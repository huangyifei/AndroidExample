package com.huangyifei.android.androidexample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.huangyifei.android.androidexample.savestate.ActivityOne;
import com.huangyifei.android.androidexample.task.MultiTaskActivity;

public class MainActivity extends BaseActivity {
    public static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchSaveState(View view) {
        ActivityOne.launch(this);
    }

    public void launchTask(View view) {
        MultiTaskActivity.launch(this);
    }
}
