package com.cemesinsurance.cemes_customer;

import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import adapter.TablayoutAdapter16;

public class Web1920_16 extends AppCompatActivity {

    ViewPager viewPager2;
    TabLayout tabLayout2;

    private Typeface mTypeface;
    private Typeface mTypefaceBold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web1920_16);

        viewPager2=findViewById(R.id.viewpager2);
        tabLayout2=findViewById(R.id.tablayout2);

        setCustomFontAndStyle(tabLayout2, 0);
        tabLayout2.setTabGravity(TabLayout.GRAVITY_FILL);


        tabLayout2.addTab(tabLayout2.newTab().setText("Overview"));
        tabLayout2.addTab(tabLayout2.newTab().setText("Account"));
        tabLayout2.addTab(tabLayout2.newTab().setText("Calculator"));

        Typeface mTypeface = Typeface.createFromAsset(getAssets(), "fonts/SF-Pro-Display-Light.otf");
        ViewGroup vg = (ViewGroup) tabLayout2.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(mTypeface, Typeface.NORMAL);
                }
            }
        }

        TablayoutAdapter16 adapter = new TablayoutAdapter16(getSupportFragmentManager(),tabLayout2.getTabCount());
        viewPager2.setAdapter(adapter);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout2));
        tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }






    private void setCustomFontAndStyle(TabLayout tabLayout, Integer position) {


        mTypeface = Typeface.createFromAsset(getAssets(), "fonts/SF-Pro-Display-Light.otf");
        mTypefaceBold = Typeface.createFromAsset(getAssets(), "fonts/SF-Pro-Display-Medium.otf");
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    if (j == position) {
                        ((TextView) tabViewChild).setTypeface(mTypefaceBold, Typeface.NORMAL);
                    } else {
                        ((TextView) tabViewChild).setTypeface(mTypeface, Typeface.NORMAL);
                    }
                }
            }
        }
    }




}
