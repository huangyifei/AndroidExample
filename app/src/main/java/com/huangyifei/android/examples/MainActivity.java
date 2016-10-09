package com.huangyifei.android.examples;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.huangyifei.android.examples.instantancestate.InstanceActivityOne;
import com.huangyifei.android.examples.instantancestate.InstanceImageFragment;
import com.huangyifei.android.examples.tasks.ReparentRootActivity;
import com.huangyifei.android.examples.tasks.StandardActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private static final long MAX_INTERVAL = 2000;
    private long mLastBackPressedTime;
    @Override
    public void onBackPressed() {
        long current = SystemClock.uptimeMillis();
        if (current - mLastBackPressedTime > MAX_INTERVAL) {
            mLastBackPressedTime = current;
            Toast.makeText(this, "press back agin to exit", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        }
    }

    public void showInstanceState(View view) {
        InstanceImageFragment.sAutoLaunch = false;
        InstanceActivityOne.launch(this);
    }

    public void showTaskActivity(View view) {
        StandardActivity.launch(this);
//        ReparentRootActivity.launch(this);
    }
}
