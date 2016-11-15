package com.huangyifei.android.androidexample;

import android.os.Bundle;
import android.view.View;

import com.huangyifei.android.androidexample.image.ImageTestActivity;
import com.huangyifei.android.androidexample.vpn.VpnActivity;

public class MainActivity extends BaseActivity {
    public static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchSaveState(View view) {
        ImageTestActivity.launch(this);
    }

    public void launchTask(View view) {
        VpnActivity.launch(this);
    }
}
