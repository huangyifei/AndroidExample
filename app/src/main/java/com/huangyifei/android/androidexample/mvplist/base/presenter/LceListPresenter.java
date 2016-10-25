package com.huangyifei.android.androidexample.mvplist.base.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.huangyifei.android.androidexample.mvplist.base.view.LceListView;

/**
 * Created by huangyifei on 16/10/21.
 */

public abstract class LceListPresenter<M> extends MvpBasePresenter<LceListView<M>> implements ILceListPresenter, LoadMorePresenter.LoadMoreListener {
    private LoadMorePresenter mLoadMorePresenter;

    @Override
    public void refreshData(boolean pullToRefresh) {
        getView().showLoading(pullToRefresh);
    }

    @Override
    public void setLoadMoreState(int state) {
        if (mLoadMorePresenter == null) return;
        mLoadMorePresenter.setLoadMoreState(state);
    }

    @Override
    public void attachView(LceListView<M> view) {
        super.attachView(view);
        refreshData(false);
    }

    @Override
    public void onLoadMore() {
        loadMoreData();
    }

    public void setLoadMorePresenter(LoadMorePresenter presenter) {
        mLoadMorePresenter = presenter;
        mLoadMorePresenter.setLoadMoreListener(this);
    }
}
