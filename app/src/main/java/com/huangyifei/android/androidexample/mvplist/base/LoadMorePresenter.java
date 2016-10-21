package com.huangyifei.android.androidexample.mvplist.base;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

/**
 * Created by huangyifei on 16/10/21.
 */

public class LoadMorePresenter extends MvpBasePresenter<LoadMoreView> {
    public static final int STATE_NORMAL = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_FINISHED = 2;
    public static final int STATE_ERROR = 3;
    private int mState;

    private LoadMoreListener mListener;

    public void setListener(LoadMoreListener listener) {
        mListener = listener;
    }

    public void setState(int state) {
        if (mState == state) return;
        if (mState == STATE_FINISHED && state == STATE_LOADING) return;
        if (isViewAttached()) {
            getView().setState(state);
        }
        mState = state;
        if (mListener != null && state == STATE_LOADING) {
            mListener.onLoadMore();
        }
    }

    public boolean isLoading() {
        return mState == STATE_LOADING;
    }

    public interface LoadMoreListener {
        void onLoadMore();
    }
}
