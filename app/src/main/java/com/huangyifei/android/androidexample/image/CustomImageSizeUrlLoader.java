package com.huangyifei.android.androidexample.image;

import android.content.Context;

import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;

/**
 * Created by huangyifei on 16/9/20.
 */
public class CustomImageSizeUrlLoader extends BaseGlideUrlLoader<CustomImageSizeModelFutureStudio> {
    public CustomImageSizeUrlLoader(Context context) {
        super(context);
    }

    @Override
    protected String getUrl(CustomImageSizeModelFutureStudio model, int width, int height) {
        return model.requestCustomSizeUrl(width, height);
    }
}
