package com.cemesinsurance.cemes_customer;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class Account_05 extends AppCompatActivity {

    TextView txt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_05);


        txt1=findViewById(R.id.txt1);

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog myDialog = new Dialog(Account_05.this);
                myDialog.getWindow();
                myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                myDialog.setCancelable(true);
                myDialog.setContentView(R.layout.dialogbox_start);
                myDialog.show();

            }
        });



    }
}
