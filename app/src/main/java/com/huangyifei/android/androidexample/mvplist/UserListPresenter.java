package com.huangyifei.android.androidexample.mvplist;

import com.huangyifei.android.androidexample.mvplist.base.LceListPresenter;
import com.huangyifei.android.androidexample.mvplist.model.UserListModel;
import com.huangyifei.android.androidexample.mvplist.model.UserListModel.UserModel;

/**
 * Created by huangyifei on 16/10/21.
 */

public class UserListPresenter extends LceListPresenter<UserModel> {

    public UserListPresenter() {
        loadData(LceListPresenter.LOAD_OTHER_REFRESH);
    }

    @Override
    public void loadData(final int loadType) {
        super.loadData(loadType);
        UserListModel.loadUserList(new UserListModel.LoadFinishListener() {
            @Override
            public void onLoadFinish(UserListModel model) {
                if (loadType == LOAD_MORE) {
                    model.mData.clear();
                    getView().addData(model);
                } else {
                    getView().setData(model);
                }
            }
        });
    }
}
