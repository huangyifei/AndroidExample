package com.huangyifei.android.examples.instantancestate;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.huangyifei.android.examples.BaseFragment;

/**
 * Created by huangyifei on 16/9/26.
 */

public class InstanceViewPagerFragment extends BaseFragment {
    int number = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey("number")) {
            number = savedInstanceState.getInt("number");
        }
        if (number == 0) {
            number = (int) SystemClock.uptimeMillis() % 100;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout linearLayout = new LinearLayout(getActivity());
        CustomImageView iv = new CustomImageView(getActivity());
        iv.setNumber(number);
        linearLayout.addView(iv, new LinearLayout.LayoutParams(200, 200));
        return linearLayout;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("number", number);
    }
}
