package com.cemesinsurance.cemes_customer;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import adapter.View_Pager_Adapter_my_saving;
import adapter.WalletAdapter1;
import adapter.WalletAdapter2;
import me.relex.circleindicator.CircleIndicator;
import model.User;
import model.WalletModel;
import model.WalletModel2;
import state.SharedPrefManager;

public class My_Wallet_07 extends AppCompatActivity {
    private TextView nameText;
    private TextView emailText;

    private WalletAdapter1 walletAdapter1;
    private RecyclerView recyclerview;
    private ArrayList<WalletModel> walletModelArrayList;

    private WalletAdapter2 walletAdapter2;
    private RecyclerView recyclerview1;
    private ArrayList<WalletModel2> walletModel2ArrayList;

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private View_Pager_Adapter_my_saving view_pager_adapter_my_saving;


    Integer oval[] = {R.drawable.ic_group_108, R.drawable.ic_group_109, R.drawable.ic_group_110, R.drawable.ic_group_111, R.drawable.ic_group_112,
            R.drawable.ic_group_113};
    String txtshopping[] = {"E-Shopping", "Bill Payment", "Charity", "Send Gift", "Split Bills", "Cash Back"};

    Integer img1[] = {R.drawable.ic_group_199, R.drawable.ic_group_109, R.drawable.ic_group_113};
    String txtsavingacct[] = {"Nearest Hospitals", "Nearest Garage", "Nearest Petrol Station"};
    String txtdiscount[] = {"Get Medical Assistance Nearest From you!", "Got up to 10% monthly interest!", "Got up to 10% monthly interest!"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_07);
        setUpViews();
        //rewards slider
        viewPager = (ViewPager) findViewById(R.id.viewpager3);
        circleIndicator = (CircleIndicator) findViewById(R.id.circleindicator3);
        view_pager_adapter_my_saving = new View_Pager_Adapter_my_saving(getSupportFragmentManager());
        viewPager.setAdapter(view_pager_adapter_my_saving);
        circleIndicator.setViewPager(viewPager);
        view_pager_adapter_my_saving.registerDataSetObserver(circleIndicator.getDataSetObserver());

        //listeners for filter activities
        ImageView hospitals = (ImageView) findViewById(R.id.hospitals);
        hospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent;
                myIntent = new Intent(My_Wallet_07.this, Login_01.class);
                startActivity(myIntent);
            }
        });


        //        promo recyclerview code is here:

        recyclerview1 = findViewById(R.id.recycler2);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(My_Wallet_07.this);
        recyclerview1.setLayoutManager(layoutManager1);
        recyclerview1.setItemAnimator(new DefaultItemAnimator());
        recyclerview1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        walletModel2ArrayList = new ArrayList<>();

        for (int i = 0; i < img1.length; i++) {
            WalletModel2 view1 = new WalletModel2(img1[i], txtsavingacct[i], txtdiscount[i]);
            walletModel2ArrayList.add(view1);
        }
        walletAdapter2 = new WalletAdapter2(My_Wallet_07.this, walletModel2ArrayList);
        recyclerview1.setAdapter(walletAdapter2);

    }

    void setUpViews() {
        //initialise Text Views
        nameText = findViewById(R.id.walletNameTextView);
        emailText = findViewById(R.id.walletEmailTextView);

        //set values based on shared preferences
        User user = SharedPrefManager.getInstance(this).getUser();
        nameText.setText(user.getName());
        emailText.setText(user.getEmail());
    }
}
