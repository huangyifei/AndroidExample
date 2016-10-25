package com.huangyifei.android.androidexample.mvplist.sample.view;

import android.view.View;
import android.widget.TextView;

import com.huangyifei.android.androidexample.mvplist.base.view.BaseViewHolder;
import com.huangyifei.android.androidexample.mvplist.sample.model.UserListModel.UserModel;

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