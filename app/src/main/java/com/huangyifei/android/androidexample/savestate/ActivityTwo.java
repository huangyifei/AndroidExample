package com.huangyifei.android.androidexample.savestate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ListView;

import com.huangyifei.android.androidexample.BaseActivity;
import com.huangyifei.android.androidexample.R;

/**
 * Created by huangyifei on 16/10/12.
 */
public class ActivityTwo extends BaseActivity {

    private static final boolean ENABLE_SAVE_STATE = false;
    private static final String KEY_PREFIX = "prefix";
    private static final String KEY_SUFFIX = "suffix";

    private String mPrefix;
    private String mSuffix;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        mListView = (ListView) findViewById(R.id.lv);

        if (ENABLE_SAVE_STATE && savedInstanceState != null) {
            // 恢复流程
            mPrefix = savedInstanceState.getString(KEY_PREFIX);
            mSuffix = savedInstanceState.getString(KEY_SUFFIX);
            mListView.setAdapter(new TwoAdapter(mPrefix, mSuffix));
        } else {
            // 初始化流程
            mPrefix = getIntent().getStringExtra(KEY_PREFIX);
            mSuffix = String.valueOf(SystemClock.uptimeMillis());
            mListView.setAdapter(new TwoAdapter(mPrefix, mSuffix));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (ENABLE_SAVE_STATE) {
            outState.putString(KEY_PREFIX, mPrefix);
            outState.putString(KEY_SUFFIX, mSuffix);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void launchOne(View view) {
        ActivityOne.launch(this);
    }

    public static void launch(Activity activity, String prefix) {
        Intent intent = new Intent(activity, ActivityTwo.class);
        intent.putExtra(KEY_PREFIX, prefix);
        activity.startActivity(intent);
    }
}