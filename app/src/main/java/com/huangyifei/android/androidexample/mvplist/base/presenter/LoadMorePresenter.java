package com.huangyifei.android.androidexample.mvplist.base.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.huangyifei.android.androidexample.mvplist.base.view.LoadMoreView;

/**
 * Created by huangyifei on 16/10/21.
 */

public class LoadMorePresenter extends MvpBasePresenter<LoadMoreView> implements ILoadMorePresenter {
    private LoadMoreListener mListener;
    private int mState;

    @Override
    public void setLoadMoreState(int state) {
        if (mState == state) return;

        if (state == STATE_LOADING && (mState == STATE_FINISHED || mState == STATE_ERROR)) return;
        if (isViewAttached()) {
            getView().setState(state);
        }
        mState = state;
        if (mListener != null && state == STATE_LOADING) {
            mListener.onLoadMore();
        }
    }

    @Override
    public void attachView(LoadMoreView view) {
        super.attachView(view);
    }

    public void setLoadMoreListener(LoadMoreListener listener) {
        mListener = listener;
    }

    public interface LoadMoreListener {
        void onLoadMore();
    }
}
