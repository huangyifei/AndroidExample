package com.huangyifei.android.examples.savestate;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.huangyifei.android.examples.BaseActivity;
import com.huangyifei.android.examples.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangyifei on 16/10/12.
 */
public class ActivityOne extends BaseActivity {

    boolean forceOom = false;
    List<Bitmap> memory = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
    }

    public void launchTwo(View view) {
        ActivityTwo.launch(this);
    }

    /**
     * 第一次点击使内存接近进程能获取的内存上限,再次点击触发OOM
     * @param view
     */
    public void consumeMem(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (!forceOom && isLowMemory()) {
                        forceOom = true;
                        break;
                    }
                    memory.add(Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }).start();
    }


    /**
     * 判断已使用的内存是否接近了单进程的内存上限
     *
     * @return
     */
    public boolean isLowMemory() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        long total = Runtime.getRuntime().totalMemory() / (1024l * 1024l);
        int max = activityManager.getMemoryClass();
        Log.w(getClass().getSimpleName(), total + "/" + max);

        if (total > activityManager.getMemoryClass() * 0.85) return true;
        return false;
    }

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, ActivityOne.class));
    }
}