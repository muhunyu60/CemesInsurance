package com.cemesinsurance.cemes_customer;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import adapter.View_Pager_Adapter_charity;
import adapter.View_Pager_Adapter_charity_2;
import me.relex.circleindicator.CircleIndicator;

public class Charity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private View_Pager_Adapter_charity view_pager_adapter_charity;

    private ViewPager viewPager1;
    private CircleIndicator circleIndicator1;
    private View_Pager_Adapter_charity_2 view_pager_adapter_charity_2;


    TextView txt1, txt2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity);


        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);


        txt1.setOnClickListener(this);
        txt2.setOnClickListener(this);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        circleIndicator = (CircleIndicator) findViewById(R.id.circleindicator);
        view_pager_adapter_charity = new View_Pager_Adapter_charity(getSupportFragmentManager());
        viewPager.setAdapter(view_pager_adapter_charity);
        circleIndicator.setViewPager(viewPager);
        view_pager_adapter_charity.registerDataSetObserver(circleIndicator.getDataSetObserver());

        viewPager1 = (ViewPager) findViewById(R.id.viewpager1);
        circleIndicator1 = (CircleIndicator) findViewById(R.id.circleindicator1);
        view_pager_adapter_charity_2 = new View_Pager_Adapter_charity_2(getSupportFragmentManager());
        viewPager1.setAdapter(view_pager_adapter_charity_2);
        circleIndicator1.setViewPager(viewPager1);
        view_pager_adapter_charity_2.registerDataSetObserver(circleIndicator1.getDataSetObserver());


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.txt1:

                txt1.setBackgroundResource(R.drawable.rect_yellow);
                txt2.setBackgroundResource(R.drawable.rect_trans_bg);

                break;

            case R.id.txt2:

                txt1.setBackgroundResource(R.drawable.rect_trans_bg);
                txt2.setBackgroundResource(R.drawable.rect_yellow);

                break;
        }


    }
}
