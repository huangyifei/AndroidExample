package com.huangyifei.android.androidexample.mvplist.base.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.layout.MvpFrameLayout;
import com.hannesdorfmann.mosby.mvp.lce.LceAnimator;
import com.huangyifei.android.androidexample.R;
import com.huangyifei.android.androidexample.mvplist.base.model.IListModel;
import com.huangyifei.android.androidexample.mvplist.base.presenter.ILoadMorePresenter;
import com.huangyifei.android.androidexample.mvplist.base.presenter.LceListPresenter;

import java.util.List;

/**
 * Created by huangyifei on 16/10/21.
 */

public abstract class LceListView<M> extends MvpFrameLayout<LceListView<M>, LceListPresenter<M>> implements ILceListView<M, IListModel<M>> {

    private ProgressBar mProgressBar;
    private SwipeRefreshLayout mContent;
    private TextView mErrorView;
    private LceListAdapter<M> mAdapter;
    private LoadMoreView mLoadMoreView;

    public LceListView(Context context) {
        super(context);
        initViews();
    }

    public LceListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public LceListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
        Context c = getContext();

        mProgressBar = new ProgressBar(c);
        mProgressBar.setId(com.hannesdorfmann.mosby.mvp.R.id.loadingView);
        mProgressBar.setIndeterminate(true);
        addView(mProgressBar, getBaseLayoutParams(true));

        mContent = new SwipeRefreshLayout(c);
        mContent.setId(com.hannesdorfmann.mosby.mvp.R.id.contentView);
        mContent.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(true);
            }
        });
        addView(mContent, getBaseLayoutParams(false));

        mErrorView = new TextView(c);
        mErrorView.setId(com.hannesdorfmann.mosby.mvp.R.id.errorView);
        addView(mErrorView, getBaseLayoutParams(true));

        RecyclerView recyclerView = new RecyclerView(c);
        recyclerView.setId(R.id.lce_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                if (lastItem + 1 == recyclerView.getLayoutManager().getItemCount()) {
                    getPresenter().setLoadMoreState(ILoadMorePresenter.STATE_LOADING);
                }
            }
        });

        mLoadMoreView = new LoadMoreView(c, this);
        mAdapter = new LceListAdapter<M>(mLoadMoreView) {
            @Override
            public BaseViewHolder<M> onCreateItemViewHolder(ViewGroup parent, int viewType) {
                return LceListView.this.createViewHolder(parent, viewType);
            }

            @Override
            public int getItemViewType(M model) {
                return LceListView.this.getItemViewType(model);
            }
        };
        recyclerView.setAdapter(mAdapter);
        mContent.addView(recyclerView, new SwipeRefreshLayout.LayoutParams(SwipeRefreshLayout.LayoutParams.MATCH_PARENT, SwipeRefreshLayout.LayoutParams.MATCH_PARENT));
    }

    private LayoutParams getBaseLayoutParams(boolean wrapContent) {
        int initPara = wrapContent ? LayoutParams.WRAP_CONTENT : LayoutParams.MATCH_PARENT;
        LayoutParams params = new LayoutParams(initPara, initPara);
        if (wrapContent) {
            params.gravity = Gravity.CENTER;
        }
        return params;
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        if (!pullToRefresh) {
            LceAnimator.showLoading(mProgressBar, mContent, mErrorView);
        } else {
            mContent.setRefreshing(true);
        }
    }

    @Override
    public void showContent() {
        if (mContent.isRefreshing()) mContent.setRefreshing(false);
        LceAnimator.showContent(mProgressBar, mContent, mErrorView);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        String errorMsg = getErrorMessage(e, pullToRefresh);
        if (pullToRefresh) {
            showLightError(errorMsg);
        } else {
            mErrorView.setText(errorMsg);
            LceAnimator.showErrorView(mProgressBar, mContent, mErrorView);
        }
    }

    @Override
    public void setData(IListModel<M> listModel) {
        mAdapter.setData(listModel.getData());
        getPresenter().setLoadMoreState(ILoadMorePresenter.STATE_NORMAL);
        showContent();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().refreshData(pullToRefresh);
    }

    @Override
    public void addData(IListModel<M> listModel) {
        if (listModel == null) {
            getPresenter().setLoadMoreState(ILoadMorePresenter.STATE_ERROR);
        } else {
            List<M> listData = listModel.getData();
            if (listData == null || listData.isEmpty()) {
                getPresenter().setLoadMoreState(ILoadMorePresenter.STATE_FINISHED);
            } else {
                mAdapter.addData(listModel.getData());
                getPresenter().setLoadMoreState(ILoadMorePresenter.STATE_NORMAL);
            }
        }
    }


    /**
     * Get the error message for a certain Exception that will be shown on {@link
     * #showError(Throwable, boolean)}
     */
    protected abstract String getErrorMessage(Throwable e, boolean pullToRefresh);

    /**
     * The default behaviour is to display a toast message as light error (i.e. pull-to-refresh
     * error).
     * Override this method if you want to display the light error in another way (like crouton).
     */
    protected void showLightError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public abstract BaseViewHolder<M> createViewHolder(ViewGroup parent, int viewType);
    public abstract int getItemViewType(M model);
}
