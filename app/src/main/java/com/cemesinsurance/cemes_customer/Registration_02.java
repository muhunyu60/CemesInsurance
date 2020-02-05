package com.cemesinsurance.cemes_customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Registration_02 extends AppCompatActivity {

    Spinner spinner_sort_by;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_02);




//spinner code is here:

        spinner_sort_by=findViewById(R.id.spinner_sort_by);

        List<String> list = new ArrayList<String>();
        list.add("code");
        list.add("+91");
        list.add("+1");
        list.add("+41");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Registration_02.this,R.layout.spinner_item,R.id.spinner_text,list);
        spinner_sort_by.setAdapter(dataAdapter);

    }
}
