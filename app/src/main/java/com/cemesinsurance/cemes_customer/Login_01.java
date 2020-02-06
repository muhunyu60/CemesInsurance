package com.cemesinsurance.cemes_customer;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import adapter.View_Pager_Adapter;
import customfonts.MyTextView_SF_Pro_Display_Medium;
import me.relex.circleindicator.CircleIndicator;
import state.SharedPrefManager;

public class Login_01 extends AppCompatActivity {

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private View_Pager_Adapter view_pager_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_01);

        if(SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, My_Wallet_07.class);
            startActivity(intent);
            finish();
        }

        //        view pager and circleindicator code is here:

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        circleIndicator = (CircleIndicator) findViewById(R.id.circleindicator);
        view_pager_adapter = new View_Pager_Adapter(getSupportFragmentManager());
        viewPager.setAdapter(view_pager_adapter);
        circleIndicator.setViewPager(viewPager);
        view_pager_adapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        MyTextView_SF_Pro_Display_Medium Login = (MyTextView_SF_Pro_Display_Medium) findViewById(R.id.login);
        MyTextView_SF_Pro_Display_Medium sign_up = (MyTextView_SF_Pro_Display_Medium) findViewById(R.id.creat_account);
    //login intent opener
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent;
                myIntent = new Intent(Login_01.this, Login.class);
                startActivity(myIntent);
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent;
                myIntent = new Intent(Login_01.this, Sign_in.class);
                startActivity(myIntent);
            }
        });

    }
}
