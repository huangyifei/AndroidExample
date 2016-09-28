package com.huangyifei.android.examples.instantancestate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.ImageView;

import com.huangyifei.android.examples.R;

/**
 * Created by huangyifei on 16/9/26.
 */

public class CustomImageView extends ImageView {
    private int number;
    private Paint paint;

    public CustomImageView(Context context) {
        super(context);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(40);
        paint.setColor(0xFF000000);
        paint.setTextAlign(Paint.Align.LEFT);
        setBackgroundColor(0xFFFF0000);
        setImageResource(R.mipmap.ic_launcher);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(String.valueOf(number), 50, 50, paint);
    }
}
