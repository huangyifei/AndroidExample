package com.huangyifei.android.examples.instantancestate;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.huangyifei.android.examples.BaseActivity;
import com.huangyifei.android.examples.BaseFragment;
import com.huangyifei.android.examples.R;
import com.huangyifei.android.examples.glide.CustomImageSizeModelFutureStudio;
import com.huangyifei.android.examples.glide.CustomImageSizeUrlLoader;

/**
 * Created by huangyifei on 16/9/26.
 */

public class InstanceImageFragment extends BaseFragment {
    public static boolean sAutoLaunch = false;


    private int imgSize = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey("size")) {
            imgSize = savedInstanceState.getInt("size");
        } else {
            if (BaseActivity.mImgSize == 0) {
                BaseActivity.mImgSize = 300;
            }
            imgSize = BaseActivity.mImgSize;
            BaseActivity.mImgSize += 4;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.w(getClass().getSimpleName(), hashCode() + "onCreateView:" + getActivity().hashCode());
        View ret = inflater.inflate(R.layout.activity_instance, null);

        ((TextView) ret.findViewById(R.id.flag)).setText("ONE");
        final ImageView iv = (ImageView) ret.findViewById(R.id.img);
        iv.getLayoutParams().width = imgSize;
        iv.getLayoutParams().height = imgSize;


        Glide.with(this)
                .using(new CustomImageSizeUrlLoader(getActivity()))
                .load(new CustomImageSizeModelFutureStudio(BaseActivity.THUMBNAIL_URL))
                .placeholder(R.mipmap.ic_launcher)
                .centerCrop()
                .listener(new RequestListener<CustomImageSizeModelFutureStudio, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, CustomImageSizeModelFutureStudio model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, CustomImageSizeModelFutureStudio model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (sAutoLaunch && BaseActivity.mImgSize < 1000) {
                            iv.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    InstanceActivityOne.launch(getActivity());
                                }
                            }, 200);
                        }
                        return false;
                    }
                })
                .into(iv);

        return ret;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("size", imgSize);
    }
}
