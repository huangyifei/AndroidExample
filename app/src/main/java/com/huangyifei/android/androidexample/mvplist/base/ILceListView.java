package com.huangyifei.android.androidexample.mvplist.base;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

/**
 * Created by huangyifei on 16/10/21.
 */

public interface ILceListView<M, LM extends IListModel<M>> extends MvpLceView<LM> {
    void loadMoreData();

    void addData(LM listModel);
}
