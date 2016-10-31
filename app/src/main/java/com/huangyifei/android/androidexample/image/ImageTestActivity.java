package com.huangyifei.android.androidexample.image;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.huangyifei.android.androidexample.BaseActivity;
import com.huangyifei.android.androidexample.R;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ContrastFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.KuwaharaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SwirlFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

/**
 * Created by huangyifei on 16/10/28.
 */
public class ImageTestActivity extends BaseActivity {

    public static final String URL = "http://78re52.com1.z0.glb.clouddn.com/resource/gogopher.jpg?imageView2";
    //http://78re52.com1.z0.glb.clouddn.com/resource/gogopher.jpg?imageView2/1/w/400

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);
        iv = (ImageView) findViewById(R.id.img);
        showDrawable(null);
    }

    public void showDrawable(View view) {
        BitmapImageViewTarget target = new BitmapImageViewTarget(iv) {

            @Override
            protected void setResource(Bitmap resource) {
                Log.d("test", "drawable: " + resource.getWidth() + "x" + resource.getHeight());
                view.setImageDrawable(new RoundedDrawable(resource, 30, 0));
            }
        };

        Glide.with(this).using(new CustomImageSizeUrlLoader(this))
                .load(new CustomImageSizeModelFutureStudio(URL))
                .asBitmap()
                .into(target);
    }

    public void showTransformation(View view) {
        String[] items = new String[11];
        final Transformation<Bitmap>[] trans = new Transformation[11];

        items[0] = "卡通-ToonFilterTransformation";
        trans[0] = new ToonFilterTransformation(this);
        items[1] = "老照片-SepiaFilterTransformation";
        trans[1] = new SepiaFilterTransformation(this);
        items[2] = "对比度-ContrastFilterTransformation";
        trans[2] = new ContrastFilterTransformation(this);
        items[3] = "反色-InvertFilterTransformation";
        trans[3] = new InvertFilterTransformation(this);
        items[4] = "像素-PixelationFilterTransformation";
        trans[4] = new PixelationFilterTransformation(this);
        items[5] = "素描-SketchFilterTransformation";
        trans[5] = new SketchFilterTransformation(this);
        items[6] = "旋涡-SwirlFilterTransformation";
        trans[6] = new SwirlFilterTransformation(this);
        items[7] = "亮度调节-BrightnessFilterTransformation";
        trans[7] = new BrightnessFilterTransformation(this);
        items[8] = "类似水彩的-KuwaharaFilterTransformation";
        trans[8] = new KuwaharaFilterTransformation(this);
        items[9] = "虚光-VignetteFilterTransformation";
        trans[9] = new VignetteFilterTransformation(this);
        items[10] = "模糊-BlurTransformation";
        trans[10] = new BlurTransformation(this);

        new AlertDialog.Builder(this)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Glide.with(ImageTestActivity.this).using(new CustomImageSizeUrlLoader(ImageTestActivity.this))
                                .load(new CustomImageSizeModelFutureStudio(URL)).bitmapTransform(trans[which]).into(iv);
                    }
                })
                .create().show();

    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, ImageTestActivity.class);
        activity.startActivity(intent);
    }

    public static class RoundedDrawable extends Drawable {

        protected final float cornerRadius;
        protected final int margin;

        protected final RectF mRect = new RectF(),
                mBitmapRect;
        protected final BitmapShader bitmapShader;
        protected final Paint paint;

        public RoundedDrawable(Bitmap bitmap, int cornerRadius, int margin) {
            this.cornerRadius = cornerRadius;
            this.margin = margin;

            bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            mBitmapRect = new RectF(margin, margin, bitmap.getWidth() - margin, bitmap.getHeight() - margin);

            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(bitmapShader);
        }

        @Override
        protected void onBoundsChange(Rect bounds) {
            super.onBoundsChange(bounds);
            mRect.set(margin, margin, bounds.width() - margin, bounds.height() - margin);

            // Resize the original bitmap to fit the new bound
            Matrix shaderMatrix = new Matrix();
            shaderMatrix.setRectToRect(mBitmapRect, mRect, Matrix.ScaleToFit.FILL);
            bitmapShader.setLocalMatrix(shaderMatrix);

        }

        @Override
        public void draw(Canvas canvas) {
            canvas.drawRoundRect(mRect, cornerRadius, cornerRadius, paint);
        }

        @Override
        public int getOpacity() {
            return PixelFormat.TRANSLUCENT;
        }

        @Override
        public void setAlpha(int alpha) {
            paint.setAlpha(alpha);
        }

        @Override
        public void setColorFilter(ColorFilter cf) {
            paint.setColorFilter(cf);
        }
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = 30;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
}