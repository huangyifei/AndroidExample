package com.huangyifei.android.androidexample.mvplist.base;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.layout.MvpLinearLayout;

/**
 * Created by huangyifei on 16/10/21.
 */

public class LoadMoreView<M> extends MvpLinearLayout<LoadMoreView, LoadMorePresenter> implements ILoadMoreView {

    private TextView mText;
    private ProgressBar mProgressBar;
    private LceListView<M> mLceListView;

    public LoadMoreView(Context context, LceListView<M> lceListView) {
        super(context);
        initViews();
        mLceListView = lceListView;
    }

    private void initViews() {
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER);

        Context c = getContext();

        mProgressBar = new ProgressBar(c);
        mProgressBar.setIndeterminate(true);
        addView(mProgressBar);

        mText = new TextView(c);
        int p = (int) (c.getResources().getDisplayMetrics().density * 20);
        mText.setPadding(p, p, p, p);
        addView(mText);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().setLoadMoreState(LoadMorePresenter.STATE_LOADING);
            }
        });
        setState(LoadMorePresenter.STATE_NORMAL);
    }

    @Override
    public LoadMorePresenter createPresenter() {
        return new LoadMorePresenter();
    }

    public void setState(int state) {
        switch (state) {
            case LoadMorePresenter.STATE_NORMAL:
                mProgressBar.setVisibility(GONE);
                mText.setText(" ");
                setClickable(false);
                break;
            case LoadMorePresenter.STATE_LOADING:
                mProgressBar.setVisibility(VISIBLE);
                mText.setText("loading");
                setClickable(false);
                break;
            case LoadMorePresenter.STATE_FINISHED:
                mProgressBar.setVisibility(GONE);
                mText.setText("finished");
                setClickable(false);
                break;
            case LoadMorePresenter.STATE_ERROR:
                mProgressBar.setVisibility(GONE);
                mText.setText("error");
                setClickable(true);
                break;
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mLceListView.getPresenter().setLoadMorePresenter(getPresenter());
    }
}
