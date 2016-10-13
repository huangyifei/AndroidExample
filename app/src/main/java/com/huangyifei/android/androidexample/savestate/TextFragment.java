package com.huangyifei.android.androidexample.savestate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huangyifei.android.androidexample.BaseFragment;

/**
 * Created by huangyifei on 16/10/13.
 */

public class TextFragment extends BaseFragment {
    public static final String KEY = "key";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());
        tv.setTextSize(25);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityOne.launch(getActivity());
            }
        });
        if (savedInstanceState != null) {
            tv.setText(String.valueOf(savedInstanceState.getInt(KEY)));
        } else {
            tv.setText("" + hashCode());
        }
        return tv;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY, hashCode());
    }
}
