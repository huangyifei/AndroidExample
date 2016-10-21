package com.huangyifei.android.androidexample.mvplist.view;

import android.view.View;
import android.widget.TextView;

import com.huangyifei.android.androidexample.mvplist.base.BaseViewHolder;
import com.huangyifei.android.androidexample.mvplist.model.UserModel;

/**
 * Created by huangyifei on 16/10/21.
 */

public class UserViewHolder extends BaseViewHolder<UserModel> {
    private TextView mUsernameView;

    public UserViewHolder(View itemView) {
        super(itemView);
        mUsernameView = (TextView) itemView;
    }

    @Override
    public void updateView(UserModel data) {
        if (null == mUsernameView) return;
        mUsernameView.setText(data.name);
    }
}