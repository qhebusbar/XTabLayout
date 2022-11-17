package com.mhy.tablayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.tabs.TabLayout;

/**
 * Created By Mahongyin
 * Date    2022/11/12 12:27
 * 在{@link TabLayout}基础上 使用 app:tabIndicatorDrawable 设置 指示器资源 图片、shape、svg gradient 等
 * 使用 app:tabIndicatorWidth 设置 指示器宽度dp
 * tabIndicatorDrawable 优先于 tabIndicatorColor 设置前者后者不生效
 * 如只需颜色和形状 可搭配使用 app:tabIndicator只获取其形状shape 和 tabIndicatorColor上色 默认 colorAccent
 */
public class MTabLayout extends TabLayout {
    private Context mContext;
    private int indicatorWidth;
    private Drawable indicatorDrawable;

    public MTabLayout(@NonNull Context context) {
        super(context);
        initLayout(context, null);
    }

    public MTabLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayout(context, attrs);
    }

    public MTabLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(context, attrs);
    }

    private void initLayout(Context context, @Nullable AttributeSet attrs) {
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, com.mhy.tablayout.R.styleable.XTabLayout);
        indicatorWidth = a.getDimensionPixelSize(R.styleable.XTabLayout_tabIndicatorWidth, 0);
        //indicatorDrawable!=null时 应用其形状颜色，宽高需另设app:tabIndicatorWidth, app:tabIndicatorHeight
        indicatorDrawable = a.getDrawable(R.styleable.XTabLayout_tabIndicatorDrawable);
        setIndicator(indicatorDrawable, indicatorWidth);
        a.recycle();
    }

    /**
     * 如果只设置app:tabIndicatorWidth 则颜色取自tabIndicatorColor
     * 如果同时设置了 app:tabIndicatorDrawable 则颜色取自 tabIndicatorDrawable
     *
     * @param indicatorWidth 设置指示器宽度
     */
    public void setIndicator(int indicatorWidth) {
        this.indicatorWidth = indicatorWidth;
        setIndicator(indicatorDrawable, indicatorWidth);
    }

    public void setIndicator(@DrawableRes int indicatorDrawableRes, int indicatorWidth) {
        if (indicatorDrawableRes != 0) {
            this.indicatorDrawable = ContextCompat.getDrawable(mContext, indicatorDrawableRes);
        } else {
            indicatorDrawable = null;
        }
        setIndicator(indicatorDrawable, indicatorWidth);
    }

    /**
     * tabIndicatorDrawable 优先级高于 tabIndicatorColor
     * 也就是设置了 app:tabIndicatorDrawable 则颜色取自 tabIndicatorDrawable 而非 tabIndicatorColor
     */
    public void setIndicator(@Nullable Drawable indicatorDrawable, int indicatorWidth) {
        if (this.indicatorDrawable != indicatorDrawable) {
            this.indicatorDrawable = indicatorDrawable;
        }
        if (indicatorWidth > 0 && !isTabIndicatorFullWidth()) {
            //注意 tabIndicatorFullWidth=false时 tabIndicatorWidth超过文本会出问题
            setTabIndicatorFullWidth(true);
        }
        IndicatorDrawable indicator = new IndicatorDrawable(indicatorDrawable, indicatorWidth);
        setSelectedTabIndicator(indicator);
    }

}
