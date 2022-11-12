package com.mhy.tablayout;


import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 原理：tabSelectedIndicator是一个Drawable，并且TabLayout有提供相应的设置方法setSelectedTabIndicator()。
 * 我们重新定义一个Drawable类，重写它的draw()方法。然后设置为tabSelectedIndicator即可。之后就可以在draw()中为所欲为了。
 */
public class IndicatorDrawable extends Drawable {

    private Paint mPaint;
    private float indicatorLeft;
    private float indicatorRight;
    private int indicatorWidth;
    private float indicatorHeight;
    //indicatorDrawable!=null时 应用其形状颜色，宽高需另设app:tabIndicatorWidth, app:tabIndicatorHeight
    private @Nullable
    Drawable indicatorDrawable;

    /**
     * 无参构造 指示器宽度随 app:tabIndicatorFullWidth 控制
     */
    public IndicatorDrawable() {
        this(0);
    }

    /**
     * @param indicatorWidth 单位px
     */
    public IndicatorDrawable(int indicatorWidth) {
        mPaint = new Paint();
        this.indicatorWidth = indicatorWidth;
    }

    /**
     * @param indicatorWidth 单位px
     */
    public IndicatorDrawable(@Nullable Drawable indicatorDrawable, int indicatorWidth) {
        this(indicatorWidth);
        this.indicatorDrawable = indicatorDrawable;
    }


    @Override
    public void draw(@NonNull Canvas canvas) {
        // 获取Drawable的真实边界，这个在调用draw之前TabLayout已经设置完毕
        indicatorLeft = getBounds().left;
        indicatorRight = getBounds().right;
        indicatorHeight = getBounds().bottom - getBounds().top;
        // 圆角半径
        float radius = indicatorHeight / 2f;
        // 默认充满
        if (indicatorWidth == 0) {
            indicatorWidth = (int) (indicatorRight - indicatorLeft);
        }
        // 指示器绘制中心
        float indicatorCenter = (indicatorRight + indicatorLeft) / 2f;
        if (indicatorLeft >= 0 && indicatorRight > indicatorLeft
                && indicatorWidth <= indicatorRight - indicatorLeft) {
            RectF rectF = new RectF(indicatorCenter - indicatorWidth / 2f, getBounds().top,
                    indicatorCenter + indicatorWidth / 2f, getBounds().bottom);

            if (indicatorDrawable != null) {
                indicatorDrawable.setBounds(
                        (int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
                indicatorDrawable.draw(canvas);
            } else {
                canvas.drawRoundRect(rectF, radius, radius, mPaint);
            }
        }
    }

    @Override
    public void setAlpha(int alpha) {
        //空实现
        //add
//        if (indicatorDrawable == null) {
//            mPaint.setAlpha(alpha);
//        } else {
//            indicatorDrawable.mutate().setAlpha(alpha);
//        }
        //add
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        //空实现
        //add ColorFilter 三个子类，ColorMatrixColorFilter,LightingColorFilter以及PorterDuffColorFilter。
//        if (indicatorDrawable == null) {
//            mPaint.setColorFilter(colorFilter);
//        } else {
//            indicatorDrawable.setColorFilter(colorFilter);
//        }
        //add
    }

    @Override
    public void setColorFilter(@ColorInt int color, @NonNull PorterDuff.Mode mode) {
        super.setColorFilter(color, mode);
        // 获取TabLayout传入的画笔颜色
        if (indicatorDrawable == null) {
            mPaint.setColor(color);
        } else {
            //indicatorDrawable.setColorFilter(color, mode);
        }
    }

    @Override
    public void setTint(int tintColor) {
        super.setTint(tintColor);
        // 获取TabLayout传入的画笔颜色
        if (indicatorDrawable == null) {
            mPaint.setColor(tintColor);
        } else {
            //indicatorDrawable.setTint(tintColor);
        }
    }

    @Override
    public int getOpacity() {
        if (indicatorDrawable == null) {
            return PixelFormat.UNKNOWN;
        } else {
            return indicatorDrawable.getOpacity();
        }
    }
}

