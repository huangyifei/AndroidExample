package com.huangyifei.android.androidexample.mvplist.base;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by huangyifei on 16/10/21.
 */

public abstract class BaseListAdapter<IM> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<IM> mData;
    private LoadMoreView mLoadMoreView;

    public BaseListAdapter(LoadMoreView loadMoreView) {
        mLoadMoreView = loadMoreView;
    }

    public void setData(List<IM> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void addData(List<IM> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            mLoadMoreView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
            return new LoadMoreViewHolder(mLoadMoreView);
        }
        return onCreateItemViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) != 1) {
            ((BaseViewHolder<IM>) viewHolder).updateView(mData.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return mData == null ? 1 : mData.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return mData == null || position == mData.size() ? 1 : 0;
    }

    public abstract BaseViewHolder<IM> onCreateItemViewHolder(ViewGroup parent, int viewType);

}
