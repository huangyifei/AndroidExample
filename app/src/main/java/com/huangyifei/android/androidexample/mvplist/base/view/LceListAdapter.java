package com.huangyifei.android.androidexample.mvplist.base.view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by huangyifei on 16/10/21.
 */

public abstract class LceListAdapter<M> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_MORE = -1;

    private List<M> mData;
    private LoadMoreView mLoadMoreView;

    public LceListAdapter(LoadMoreView loadMoreView) {
        mLoadMoreView = loadMoreView;
    }

    public void setData(List<M> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void addData(List<M> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_MORE) {
            mLoadMoreView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
            return new LoadMoreViewHolder(mLoadMoreView);
        }
        return onCreateItemViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) != VIEW_TYPE_MORE) {
            ((BaseViewHolder<M>) viewHolder).updateView(mData.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return mData == null ? 1 : mData.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return mData == null || position == mData.size() ? VIEW_TYPE_MORE : getItemViewType(mData.get(position));
    }

    public abstract BaseViewHolder<M> onCreateItemViewHolder(ViewGroup parent, int viewType);
    public abstract int getItemViewType(M model);

}
