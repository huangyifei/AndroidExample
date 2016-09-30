package com.huangyifei.android.examples.tasks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.huangyifei.android.examples.BaseActivity;
import com.huangyifei.android.examples.R;

/**
 * Created by huangyifei on 16/9/30.
 */
public class SingleTaskAcitivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        ((TextView) findViewById(R.id.flag)).setText("SingleTask");
    }


    public void launchNormal(View view) {
        StandardActivity.launchNewTask(this);
    }

    public void launchSingleTask(View view) {
        SingleTaskAcitivity.launch(this);
    }


    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, SingleTaskAcitivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
}