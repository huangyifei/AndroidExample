package com.huangyifei.android.androidexample.mvplist.base.view;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;
import com.huangyifei.android.androidexample.mvplist.base.model.IListModel;

/**
 * Created by huangyifei on 16/10/21.
 */

public interface ILceListView<M, LM extends IListModel<M>> extends MvpLceView<LM> {

    void addData(LM listModel);

}
