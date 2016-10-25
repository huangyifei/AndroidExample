package com.huangyifei.android.androidexample.mvplist;

import com.huangyifei.android.androidexample.mvplist.base.LceListPresenter;
import com.huangyifei.android.androidexample.mvplist.model.UserListModel;
import com.huangyifei.android.androidexample.mvplist.model.UserListModel.UserModel;

/**
 * Created by huangyifei on 16/10/21.
 */

public class UserListPresenter extends LceListPresenter<UserModel> {

    @Override
    public void refreshData(boolean pullToRefresh) {
        super.refreshData(pullToRefresh);
        UserListModel.loadUserList(new UserListModel.LoadFinishListener() {
            @Override
            public void onLoadFinish(UserListModel model) {
                getView().setData(model);
            }
        });
    }

    @Override
    public void loadMoreData() {
        UserListModel.loadUserList(new UserListModel.LoadFinishListener() {
            @Override
            public void onLoadFinish(UserListModel model) {
                model.mData.clear();
                getView().addData(model);
            }
        });
    }
}
