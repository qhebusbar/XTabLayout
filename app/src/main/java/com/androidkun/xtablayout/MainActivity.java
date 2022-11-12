package com.androidkun.xtablayout;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.mhy.tablayout.MTabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Fragment> fragments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewPager();
    }

    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("一本");
        titles.add("二本");
        titles.add("三A");
        titles.add("三B");
        titles.add("一本");
        titles.add("二本");
        titles.add("三A");
        titles.add("三B");
        for (int i = 0; i < titles.size(); i++) {
            if(i%2==0){
                fragments.add(new Fragment2());
            }else{
                fragments.add(new Fragment1());
            }
        }
        FragmentAdapter adatper = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adatper);
        viewPager.setOffscreenPageLimit(4);
        //将TabLayout和ViewPager关联起来。
        final XTabLayout tabLayout = (XTabLayout) findViewById(R.id.xTablayout);
        //给TabLayout设置适配器
        tabLayout.setupWithViewPager(viewPager);

        final XTabLayout tabLayout2 = (XTabLayout) findViewById(R.id.xTablayout2);
        //给TabLayout设置适配器
        tabLayout2.setupWithViewPager(viewPager);

        final MTabLayout tabLayout3 = findViewById(R.id.xTablayout3);
        //给TabLayout设置适配器
        tabLayout3.setupWithViewPager(viewPager);
//        tabLayout3.setIndicator(R.drawable.ic_indicator,32);
    }

    private float dip2px(Context mContext, float size) {
        if (size == 0) {
            return 0;
        }
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size,
                mContext.getResources().getDisplayMetrics());
    }
}
