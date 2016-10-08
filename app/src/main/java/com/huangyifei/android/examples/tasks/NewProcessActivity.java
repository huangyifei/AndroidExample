package com.huangyifei.android.examples.tasks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.huangyifei.android.examples.BaseActivity;
import com.huangyifei.android.examples.R;
import com.huangyifei.android.examples.instantancestate.InstanceActivityOne;
import com.huangyifei.android.examples.instantancestate.InstanceActivityTwo;

/**
 * Created by huangyifei on 16/10/8.
 */
public class NewProcessActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_process);
    }

    public void launchSingleInstance(View view) {
        InstanceActivityTwo.launch(this);
    }

    public void launchOne(View view) {
        InstanceActivityOne.launch(this);
    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, NewProcessActivity.class);
        activity.startActivity(intent);
    }
}