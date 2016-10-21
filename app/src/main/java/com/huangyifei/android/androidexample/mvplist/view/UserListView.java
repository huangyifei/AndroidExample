package com.huangyifei.android.androidexample.mvplist.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huangyifei.android.androidexample.mvplist.UserListPresenter;
import com.huangyifei.android.androidexample.mvplist.base.BaseViewHolder;
import com.huangyifei.android.androidexample.mvplist.base.LceListPresenter;
import com.huangyifei.android.androidexample.mvplist.base.LceListView;
import com.huangyifei.android.androidexample.mvplist.model.UserModel;

/**
 * Created by huangyifei on 16/10/21.
 */

public class UserListView extends LceListView<UserModel> {

    public UserListView(Context context) {
        super(context);
    }

    public UserListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public UserListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LceListPresenter<UserModel> createPresenter() {
        return new UserListPresenter();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public BaseViewHolder<UserModel> createViewHolder(ViewGroup parent, int viewType) {
        return new UserViewHolder(new TextView(parent.getContext()));
    }
}
