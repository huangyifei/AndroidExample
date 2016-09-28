package com.huangyifei.android.examples.instantancestate;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.huangyifei.android.examples.BaseActivity;
import com.huangyifei.android.examples.R;

/**
 * Created by huangyifei on 16/9/26.
 */

public class InstanceActivityTwo extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityManager am = null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp);
        viewPager.setOffscreenPageLimit(1);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(9);
    }

    public void launchOne(View view) {
        InstanceActivityOne.launch(this);
    }

    public void launchTwo(View view) {
        launch(this);
    }

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, InstanceActivityTwo.class));
    }


    public class MyAdapter extends FragmentStatePagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 19;
        }

        @Override
        public Fragment getItem(int position) {
            Log.w("test", "getItem:" + position);
            Fragment fragment = Fragment.instantiate(InstanceActivityTwo.this, InstanceViewPagerFragment.class.getName());
            Bundle args = new Bundle();
            args.putInt("key", position);
            fragment.setArguments(args);
            return fragment;
        }
    }
}
