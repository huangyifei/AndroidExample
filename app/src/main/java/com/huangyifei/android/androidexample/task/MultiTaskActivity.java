package com.huangyifei.android.androidexample.task;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.huangyifei.android.androidexample.BaseActivity;

/**
 * Created by huangyifei on 16/10/18.
 */
public class MultiTaskActivity extends BaseActivity {
    public static final String TAG = MultiTaskActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setTextSize(20);
        tv.setText("MultiTaskActivity");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiTaskActivity.launchNewTask(MultiTaskActivity.this);
            }
        });
        setContentView(tv);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, MultiTaskActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
//        intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        activity.startActivity(intent);
    }

    public static void launchNewTask(Activity activity) {
        Intent intent = new Intent(activity, MultiTaskActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        activity.startActivity(intent);
    }
}