package com.huangyifei.android.androidexample.mvplist.base;

/**
 * Created by huangyifei on 16/10/25.
 */

public interface ILceListPresenter extends ILoadMorePresenter {
    void refreshData(boolean pullToRefresh);

    void loadMoreData();
}
