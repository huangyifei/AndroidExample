package com.huangyifei.android.examples.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by huangyifei on 16/9/20.
 */
public class SimpleGlideModule implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888)
                .setDiskCache(new DiskLruCacheFactory("", 100));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
    }

}
