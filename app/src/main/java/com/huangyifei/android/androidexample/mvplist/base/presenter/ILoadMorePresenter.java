package com.huangyifei.android.androidexample.mvplist.base.presenter;

/**
 * Created by huangyifei on 16/10/25.
 */

public interface ILoadMorePresenter {
    int STATE_NORMAL = 0;
    int STATE_LOADING = 1;
    int STATE_FINISHED = 2;
    int STATE_ERROR = 3;

    void setLoadMoreState(int state);
}
