package com.huangyifei.android.androidexample.vpn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.VpnService;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;

import com.huangyifei.android.androidexample.BaseActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import de.blinkt.openvpn.OpenVpnApi;
import de.blinkt.openvpn.VpnProfile;
import de.blinkt.openvpn.core.ConfigParser;
import de.blinkt.openvpn.core.ProfileManager;
import de.blinkt.openvpn.core.VPNLaunchHelper;
import de.blinkt.openvpn.core.VpnStatus;

/**
 * Created by huangyifei on 16/11/8.
 */
public class VpnActivity extends BaseActivity implements View.OnClickListener {
    public static final String TAG = VpnActivity.class.getName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button button = new Button(this);
        button.setText("connect");
        button.setOnClickListener(this);
        setContentView(button);

        VpnStatus.initLogCache(getApplicationContext().getCacheDir());
    }

    @Override
    public void onClick(View v) {
        startVpn();
    }

    private void startVpn() {
        try {
            InputStream conf = getAssets().open("private.ovpn");
            InputStreamReader isr = new InputStreamReader(conf);
            BufferedReader br = new BufferedReader(isr);
            String config = "";
            String line;
            while (true) {
                line = br.readLine();
                if (line == null)
                    break;
                config += line + "\n";
            }
            br.readLine();
            OpenVpnApi.startVpn(this, config, null, null);
        } catch (IOException | RemoteException e) {
            e.printStackTrace();
        }
    }


    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, VpnActivity.class));
    }
}