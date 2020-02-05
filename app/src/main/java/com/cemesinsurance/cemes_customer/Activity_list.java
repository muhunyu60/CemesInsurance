package com.cemesinsurance.cemes_customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import adapter.ListAdapter;
import model.ListModel;

public class Activity_list extends AppCompatActivity {

    private ListAdapter listAdapter;
    private RecyclerView recyclerview;
    private ArrayList<ListModel> listModelArrayList;

    String txt[]={"1.Login","2.Registration","3.Verification","4.Fingerprint",
            "5.Account","6.My Wallet","7.Charity","8.Gift","9.Saving DashBoard","10.Insites"
            ,"11.Stats","12.Loan","13.Insurance","14.Loan2","15.Payment Success"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerview=findViewById(R.id.recyclerView1);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(Activity_list.this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());

        listModelArrayList = new ArrayList<>();

        for (int i=0;i<txt.length;i++){

            ListModel listModel = new ListModel(txt[i]);

            listModelArrayList.add(listModel);

        }
        listAdapter = new ListAdapter(Activity_list.this,listModelArrayList);
        recyclerview.setAdapter(listAdapter);
    }
}
