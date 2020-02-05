package com.cemesinsurance.cemes_customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import customfonts.MyTextView_SF_Pro_Display_Medium;

public class Sign_in extends AppCompatActivity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        MyTextView_SF_Pro_Display_Medium Login = (MyTextView_SF_Pro_Display_Medium) findViewById(R.id.login);
        //login intent opener
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent;
                myIntent = new Intent(Sign_in.this, My_Wallet_07.class);
                startActivity(myIntent);
            }
        });


    }
}
