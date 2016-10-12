package com.huangyifei.android.examples.instantancestate;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.huangyifei.android.examples.BaseActivity;
import com.huangyifei.android.examples.R;

/**
 * Created by huangyifei on 16/9/26.
 */

public class InstanceActivityOne extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container_layout);
        if (savedInstanceState == null || !savedInstanceState.containsKey("old")) {
            getSupportFragmentManager().beginTransaction().add(R.id.content, Fragment.instantiate(this, InstanceImageFragment.class.getName())).commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("old", 1);
    }

    public void launchOne(View view) {
        if (!InstanceImageFragment.sAutoLaunch) {
            InstanceImageFragment.sAutoLaunch = true;
            InstanceActivityOne.launch(this);
        } else {
            String test = null;
            test.length();
        }

    }

    public void launchTwo(View view) {
        InstanceActivityTwo.launch(this);
    }

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, InstanceActivityOne.class));
    }
}
