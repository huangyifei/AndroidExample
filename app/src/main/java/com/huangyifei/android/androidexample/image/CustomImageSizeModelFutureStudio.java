package com.huangyifei.android.androidexample.image;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huangyifei on 16/9/20.
 */
public class CustomImageSizeModelFutureStudio {
    String baseImageUrl;

    public CustomImageSizeModelFutureStudio(String baseImageUrl) {
        this.baseImageUrl = baseImageUrl;
    }

    public String requestCustomSizeUrl(int width, int height) {
        Pattern pattern = Pattern.compile("imageView2$");
        Matcher matcher = pattern.matcher(baseImageUrl);
        if (matcher.find()) {
            // /1/w会将图片裁切为正方形
            Log.d("test", "requestCustomSizeUrl: " + baseImageUrl + "/1/w/" + width);
            return baseImageUrl + "/1/w/" + width;
        }
        return baseImageUrl;
    }
}
