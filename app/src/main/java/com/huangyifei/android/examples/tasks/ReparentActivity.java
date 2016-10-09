package com.huangyifei.android.examples.tasks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.huangyifei.android.examples.BaseActivity;

/**
 * Created by huangyifei on 16/10/8.
 */
public class ReparentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("reparent");
        tv.setTextSize(50);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReparentRootActivity.launch(ReparentActivity.this);
            }
        });
        setContentView(tv);
    }

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, ReparentActivity.class));
    }


    public static void launchInNewTask(Activity activity) {
        Intent intent = new Intent(activity, ReparentActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
}