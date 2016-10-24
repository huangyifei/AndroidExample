package com.huangyifei.android.androidexample.mvplist.base;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

/**
 * Created by huangyifei on 16/10/21.
 */

public class LceListPresenter<M> extends MvpBasePresenter<LceListView<M>> {
    public static final int LOAD_PULL_REFRESH = 1;
    public static final int LOAD_OTHER_REFRESH = 2;
    public static final int LOAD_MORE = 3;


    public void loadData(int loadType) {
        if (!isViewAttached()) return;
        switch (loadType) {
            case LOAD_PULL_REFRESH:
                getView().showLoading(true);
                break;
            case LOAD_OTHER_REFRESH:
                getView().showLoading(false);
                break;
            case LOAD_MORE:
                //TODO change load more view
                break;
            default:
                break;
        }
    }
}
