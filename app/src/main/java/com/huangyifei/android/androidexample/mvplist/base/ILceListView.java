package com.huangyifei.android.androidexample.mvplist.base;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

/**
 * Created by huangyifei on 16/10/21.
 */

public interface ILceListView<IM, M extends IListModel<IM>> extends MvpLceView<M> {
    public void loadMoreData();

    public void addData(M data);
}
