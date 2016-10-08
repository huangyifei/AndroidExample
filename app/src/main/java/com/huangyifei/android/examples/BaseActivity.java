package com.huangyifei.android.examples;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

/**
 * Created by huangyifei on 16/9/26.
 */

public class BaseActivity extends FragmentActivity {
    public static final String THUMBNAIL_URL = "http://78re52.com1.z0.glb.clouddn.com/resource/gogopher.jpg?imageView2";

    public static int mImgSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> l = am.getRunningAppProcesses();
        for (int i = 0; i < l.size(); i++) {
            ActivityManager.RunningAppProcessInfo api = l.get(i);
            if (api.pid == Process.myPid()) {
                Log.w(getClass().getSimpleName(), hashCode() + "onCreate:" + getTaskId() + "|process:" + api.processName);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(getClass().getSimpleName(), hashCode() + "onStart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.w(getClass().getSimpleName(), hashCode() + "onRestoreInstanceState");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(getClass().getSimpleName(), hashCode() + "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(getClass().getSimpleName(), hashCode() + "onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.w(getClass().getSimpleName(), hashCode() + "onSaveInstanceState");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(getClass().getSimpleName(), hashCode() + "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(getClass().getSimpleName(), hashCode() + "onDestroy");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.w(getClass().getSimpleName(), hashCode() + "onNewIntent");
    }
}
