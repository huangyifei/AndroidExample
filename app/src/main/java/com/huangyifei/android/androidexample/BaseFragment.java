package com.huangyifei.android.androidexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by huangyifei on 16/9/26.
 */

public class BaseFragment extends Fragment {
    public static final String KEY = "key";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w(getClass().getSimpleName(), hashCode() + "-onCreate:");
        checkInstance(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.w(getClass().getSimpleName(), hashCode() + "-onCreateView:" + getActivity().hashCode());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(getClass().getSimpleName(), hashCode() + "-onViewCreated:");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.w(getClass().getSimpleName(), hashCode() + "-onViewStateRestored:");
        checkInstance(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY, hashCode());
        Log.w(getClass().getSimpleName(), hashCode() + "-onSaveInstanceState:");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.w(getClass().getSimpleName(), hashCode() + "-onDestroy:" + getActivity().hashCode());
    }

    private void checkInstance(Bundle instance) {
        if (instance == null) {
            Log.w(getClass().getSimpleName(), hashCode() + " empty instance");
        } else if (instance.containsKey(KEY)) {
            Log.w(getClass().getSimpleName(), hashCode() + "instance:" + instance.get(KEY));
        }
    }
}
