package com.cemesinsurance.cemes_customer;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import adapter.View_Pager_Adapter_my_saving;
import me.relex.circleindicator.CircleIndicator;

public class My_Saving_11 extends AppCompatActivity implements View.OnClickListener {


    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private View_Pager_Adapter_my_saving view_pager_adapter_my_saving;

    TextView txt1, txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__saving_11);


        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);


        txt1.setOnClickListener(this);
        txt2.setOnClickListener(this);

        viewPager = (ViewPager) findViewById(R.id.viewpager3);
        circleIndicator = (CircleIndicator) findViewById(R.id.circleindicator3);
        view_pager_adapter_my_saving = new View_Pager_Adapter_my_saving(getSupportFragmentManager());
        viewPager.setAdapter(view_pager_adapter_my_saving);
        circleIndicator.setViewPager(viewPager);
        view_pager_adapter_my_saving.registerDataSetObserver(circleIndicator.getDataSetObserver());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {


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
