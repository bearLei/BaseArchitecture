package com.ubtech.base_lib.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.core.content.ContextCompat;


/**
 * Created by Yxtao on 2018/1/26.
 */

public class BitmapUtils {


    public static Bitmap blur(Context context, Bitmap bitmap, float radius) {
        if (bitmap != null) {
            Bitmap output = Bitmap.createBitmap(bitmap);
            // 创建输出图片
            RenderScript rs = RenderScript.create(context);
            // 构建一个RenderScript对象
            ScriptIntrinsicBlur gaussianBlue = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            // 创建高斯模糊脚本
            Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
            // 创建用于输入的脚本类型
            Allocation allOut = Allocation.createFromBitmap(rs, output);
            // 创建用于输出的脚本类型
            gaussianBlue.setRadius(radius);
            // 设置模糊半径，范围0f<radius<=25f
            gaussianBlue.setInput(allIn);
            // 设置输入脚本类型
            gaussianBlue.forEach(allOut);
            // 执行高斯模糊算法，并将结果填入输出脚本类型中
            allOut.copyTo(output);

            gaussianBlue.destroy();
            allOut.destroy();
            allIn.destroy();
            // 将输出内存编码为Bitmap，图片大小必须注意
            rs.destroy();
            // 关闭RenderScript对象，API>=23则使用
            // rs.releaseAllContexts()
            return output;
        }
        return null;
    }


    public static int computeInSampleSize(BitmapFactory.Options opts, int reqWidth, int reqHeight) {
        final int height = opts.outHeight;
        final int width = opts.outWidth;
        int sampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / sampleSize) > reqHeight || (halfWidth / sampleSize) > halfWidth)
                sampleSize *= 2;
        }
//        int heightRatio = height / reqHeight;
//        int widthRatio = width / reqHeight;
        return sampleSize;
    }

    public static Bitmap decodeInSampleBitmap(String filePath, int reqWidth, int reqHeight) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        //设置inJustDecodeBounds=true 获取图片像素的宽高
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, opts);
        //计算inSampleSize 加载图片的比例
        opts.inSampleSize = computeInSampleSize(opts, reqWidth, reqHeight);
        //加载图片
        opts.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, opts);
    }


    public static Bitmap gaussianBlur(Context mContext, View comBitmap) {
        Bitmap bitmap = null;
        if (mContext == null) return bitmap;
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();

        int mHeightPixels = displayMetrics.heightPixels;
        int mWidthPixels = displayMetrics.widthPixels;

        if (comBitmap != null) {
            comBitmap.clearFocus();
            comBitmap.setPressed(false);

            boolean willNotCache = comBitmap.willNotCacheDrawing();
            comBitmap.setWillNotCacheDrawing(false);
            int color = comBitmap.getDrawingCacheBackgroundColor();
            comBitmap.setDrawingCacheBackgroundColor(0);
            float alpha = comBitmap.getAlpha();
            comBitmap.setAlpha(1.0f);

            if (color != 0) {
                comBitmap.destroyDrawingCache();
            }

            int widthSpec = View.MeasureSpec.makeMeasureSpec(mWidthPixels, View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(mHeightPixels, View.MeasureSpec.EXACTLY);
            comBitmap.measure(widthSpec, heightSpec);
            comBitmap.layout(0, 0, mWidthPixels, mHeightPixels);
            comBitmap.bringToFront();


            comBitmap.buildDrawingCache();
            Bitmap cacheBitmap = comBitmap.getDrawingCache();
            if (cacheBitmap == null) {
                return null;
            }
            bitmap = Bitmap.createBitmap(cacheBitmap);
            // Restore the view
            comBitmap.setAlpha(alpha);
            comBitmap.destroyDrawingCache();
            comBitmap.setWillNotCacheDrawing(willNotCache);
            comBitmap.setDrawingCacheBackgroundColor(color);
        }
        Bitmap blur = BitmapUtils.blur(mContext, bitmap, 25);
        return blur;
    }


    public static Bitmap drawable2Bitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }






    public static Bitmap drawable2Bitmap(Context context, int id) {
        Drawable drawable = ContextCompat.getDrawable(context, id);
        Bitmap deleteBitmap = BitmapUtils.drawable2Bitmap(drawable);
        return deleteBitmap;
    }


    public static Bitmap drawable2Bitmap(Context context, int id, int w, int h) {
        Drawable drawable = ContextCompat.getDrawable(context, id);
        Bitmap bitmap = Bitmap
                .createBitmap(
                        w,
                        h,
                        Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w,
                h);
        drawable.draw(canvas);
        return bitmap;
    }
}
