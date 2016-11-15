package de.blinkt.openvpn;

import android.content.Context;
import android.content.Intent;
import android.net.VpnService;
import android.os.RemoteException;
import android.text.TextUtils;

import java.io.IOException;
import java.io.StringReader;

import de.blinkt.openvpn.core.ConfigParser;
import de.blinkt.openvpn.core.ProfileManager;
import de.blinkt.openvpn.core.VPNLaunchHelper;

/**
 * Created by huangyifei on 16/11/15.
 */

public class OpenVpnApi {

    /**
     *
     * @param context
     * @param inlineConfig 一般都保存在文件中，通过读取文件获取配置信息
     * @param userName 某些ovpn的连接方式需要用户名和密码，可以为空
     * @param pw 某些ovpn的连接方式需要用户名和密码，可以为空
     * @throws RemoteException
     */
    public static void startVpn(Context context, String inlineConfig, String userName, String pw) throws RemoteException {
        if (TextUtils.isEmpty(inlineConfig)) throw new RemoteException("config is empty");
        Intent intent = VpnService.prepare(context);
        if (intent != null) {
            intent = new Intent(context, VpnAuthAcitivity.class);
            intent.putExtra(VpnAuthAcitivity.KEY_CONFIG, inlineConfig);
            if (!TextUtils.isEmpty(userName)) {
                intent.putExtra(VpnAuthAcitivity.KEY_USERNAME, userName);
            }
            if (!TextUtils.isEmpty(pw)) {
                intent.putExtra(VpnAuthAcitivity.KEY_PASSWORD, pw);
            }
            context.startActivity(intent);
        } else {
            startVpnInternal(context, inlineConfig, userName, pw);
        }

    }

    static void startVpnInternal(Context context, String inlineConfig, String userName, String pw) throws RemoteException {
        ConfigParser cp = new ConfigParser();
        try {
            cp.parseConfig(new StringReader(inlineConfig));
            VpnProfile vp = cp.convertProfile();
            vp.mName = "Remote APP VPN";
            if (vp.checkProfile(context) != de.blinkt.openvpn.R.string.no_error_found)
                throw new RemoteException(context.getString(vp.checkProfile(context)));

            vp.mProfileCreator = context.getPackageName();
            vp.mUsername = userName;
            vp.mPassword = pw;

            ProfileManager.setTemporaryProfile(vp);

            VPNLaunchHelper.startOpenVpn(vp, context);

        } catch (IOException | ConfigParser.ConfigParseError e) {
            throw new RemoteException(e.getMessage());
        }
    }
}
