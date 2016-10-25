package com.huangyifei.android.androidexample.mvplist.base.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by huangyifei on 16/10/21.
 */

public abstract class BaseViewHolder<M> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void updateView(M model);
}
