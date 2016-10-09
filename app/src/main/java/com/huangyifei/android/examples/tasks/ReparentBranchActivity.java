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
public class ReparentBranchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("branch");
        tv.setTextSize(50);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReparentActivity.launch(ReparentBranchActivity.this);
            }
        });
        setContentView(tv);
    }

    public static void launchInNewTask(Activity activity) {
        Intent intent = new Intent(activity, ReparentBranchActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
}