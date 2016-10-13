package com.huangyifei.android.androidexample.savestate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.huangyifei.android.androidexample.BaseActivity;
import com.huangyifei.android.androidexample.R;

/**
 * Created by huangyifei on 16/10/13.
 */
public class ActivityThree extends BaseActivity {

    private static final boolean ENABLE_SAVE_STATE = true;

    private TextFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container_layout);

        if (ENABLE_SAVE_STATE && savedInstanceState != null) {
            mFragment = (TextFragment) getSupportFragmentManager().findFragmentByTag("text_fragment");
        } else {
            mFragment = (TextFragment) Fragment.instantiate(this, TextFragment.class.getName());
            getSupportFragmentManager().beginTransaction().add(R.id.content, mFragment, "text_fragment").commit();
        }
    }

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, ActivityThree.class));
    }
}