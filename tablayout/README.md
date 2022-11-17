
1.仅设置指示器宽高和颜色(tabIndicatorColor)
    <com.mhy.xtablayout.XTabLayout
        android:id="@+id/xTablayout3"
        android:layout_width="match_parent"
        android:layout_height="130px"
        app:tabIndicatorWidth="32dp"
        app:tabIndicatorHeight="4dp"
        app:tabIndicatorColor="@color/colorPrimary"
        app:tabMode="scrollable"
        app:tabSelectedTextColor="#ffff00"
        app:tabTextColor="#000000" />    
2.仅设置指示器宽高、形状(tabIndicator)和颜色(tabIndicatorColor)
    <com.mhy.xtablayout.XTabLayout
        android:id="@+id/xTablayout4"
        android:layout_width="match_parent"
        android:layout_height="130px"
        app:tabIndicatorWidth="32dp"
        app:tabIndicatorHeight="4dp"
        app:tabIndicator="@drawable/shape_indicator"
        app:tabIndicatorColor="@color/colorPrimary"
        app:tabMode="scrollable"
        app:tabSelectedTextColor="#ffff00"
        app:tabTextColor="#000000" />
3.设置指示器宽高和(tabIndicatorDrawable) : 渐变色、圆角、图 均可
    <com.mhy.xtablayout.XTabLayout
        android:id="@+id/xTablayout4"
        android:layout_width="match_parent"
        android:layout_height="130px"
        app:tabIndicatorWidth="32dp"
        app:tabIndicatorHeight="4dp"
        app:tabIndicatorDrawable="@drawable/ic_indicator"
        app:tabMode="scrollable"
        app:tabSelectedTextColor="#ffff00"
        app:tabTextColor="#000000" />