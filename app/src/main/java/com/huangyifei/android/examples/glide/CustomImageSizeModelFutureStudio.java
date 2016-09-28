package com.huangyifei.android.examples.glide;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huangyifei on 16/9/20.
 */
public class CustomImageSizeModelFutureStudio{
    String baseImageUrl;

    public CustomImageSizeModelFutureStudio(String baseImageUrl) {
        this.baseImageUrl = baseImageUrl;
    }

    public String requestCustomSizeUrl(int width, int height) {
        Pattern pattern = Pattern.compile("imageView2$");
        Matcher matcher = pattern.matcher(baseImageUrl);
        if (matcher.find()) {
            Log.d("test", "find:" + baseImageUrl + "|" + width);
            return baseImageUrl + "/2/w/" + width;
        }
        return baseImageUrl;
    }
}
