package com.huangyifei.android.androidexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * Created by huangyifei on 16/9/26.
 */

public class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w(getClass().getSimpleName(), hashCode() + "-onCreate: TaskId-" + getTaskId());

        // 打印Activity所在的进程
//        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//        List<ActivityManager.RunningAppProcessInfo> l = am.getRunningAppProcesses();
//        for (int i = 0; i < l.size(); i++) {
//            ActivityManager.RunningAppProcessInfo api = l.get(i);
//            if (api.pid == Process.myPid()) {
//                Log.w(getClass().getSimpleName(), hashCode() + "onCreate:" + getTaskId() + "|process:" + api.processName);
//            }
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(getClass().getSimpleName(), hashCode() + "-onStart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.w(getClass().getSimpleName(), hashCode() + "-onRestoreInstanceState");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(getClass().getSimpleName(), hashCode() + "-onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(getClass().getSimpleName(), hashCode() + "-onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.w(getClass().getSimpleName(), hashCode() + "-onSaveInstanceState");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(getClass().getSimpleName(), hashCode() + "-onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(getClass().getSimpleName(), hashCode() + "-onDestroy");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.w(getClass().getSimpleName(), hashCode() + "-onNewIntent");
    }

}
