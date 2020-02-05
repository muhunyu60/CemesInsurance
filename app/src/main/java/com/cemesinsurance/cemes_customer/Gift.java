package com.cemesinsurance.cemes_customer;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import adapter.View_Pager_Adapter_gift;

public class Gift extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private View_Pager_Adapter_gift view_pager_adapter_gift;


    LinearLayout l1, l2, l3, l4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift);


        viewPager = findViewById(R.id.viewpager4);


        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);
        l3 = findViewById(R.id.l3);
        l4 = findViewById(R.id.l4);


        l1.setOnClickListener(this);
        l2.setOnClickListener(this);
        l3.setOnClickListener(this);
        l4.setOnClickListener(this);


        viewPager = (ViewPager) findViewById(R.id.viewpager4);
        view_pager_adapter_gift = new View_Pager_Adapter_gift(getSupportFragmentManager());
        viewPager.setAdapter(view_pager_adapter_gift);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.l1:

                viewPager.setCurrentItem(0);

                break;

            case R.id.l2:

                viewPager.setCurrentItem(1);

                break;

            case R.id.l3:

                viewPager.setCurrentItem(2);

                break;
            case R.id.l4:

                viewPager.setCurrentItem(3);

                break;
        }

    }
}
