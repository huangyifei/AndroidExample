package com.huangyifei.android.androidexample.savestate;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by huangyifei on 16/10/13.
 */

public class TwoAdapter extends BaseAdapter {

    private String mPrefix;
    private String mSuffix;

    public TwoAdapter(String prefix, String suffix) {
        mPrefix = prefix;
        mSuffix = suffix;
    }

    @Override
    public int getCount() {
        return 30;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new TextView(parent.getContext());
            ((TextView) convertView).setTextSize(25);
        }
        ((TextView) convertView).setText(mPrefix + "-" + position + "-" + mSuffix);
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }
}
