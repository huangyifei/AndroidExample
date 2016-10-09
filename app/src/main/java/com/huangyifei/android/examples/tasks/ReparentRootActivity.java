package com.huangyifei.android.examples.tasks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.huangyifei.android.examples.BaseActivity;
import com.huangyifei.android.examples.R;

/**
 * Created by huangyifei on 16/10/8.
 */
public class ReparentRootActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reparent);
    }

    public void launchStandard(View view) {
        StandardActivity.launchNewTask(this);
    }

    public void launchReparent(View view) {
        ReparentBranchActivity.launchInNewTask(this);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, ReparentRootActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
}