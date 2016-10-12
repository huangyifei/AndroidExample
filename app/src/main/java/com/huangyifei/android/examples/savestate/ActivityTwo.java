package com.huangyifei.android.examples.savestate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.huangyifei.android.examples.BaseActivity;
import com.huangyifei.android.examples.R;


/**
 * Created by huangyifei on 16/10/12.
 */
public class ActivityTwo extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
    }

    public void launchOne(View view) {
        ActivityOne.launch(this);
    }

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, ActivityTwo.class));
    }
}