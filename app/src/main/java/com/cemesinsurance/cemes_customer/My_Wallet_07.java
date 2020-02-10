package com.cemesinsurance.cemes_customer;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import fragment.Fragment_Home;
import fragment.Fragment_Claims;
import fragment.Fragment_SOS;

public class My_Wallet_07 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_07);

        //Load the Home Page by default
        loadFragment(new Fragment_Home());

        //set up bottom navigation view
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        //Add behaviour
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehaviour());
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.bottom_navigation_home:
                    fragment = new Fragment_Home();
                    loadFragment(fragment);
                    return true;
                case R.id.bottom_navigation_claims:
                    fragment = new Fragment_Claims();
                    loadFragment(fragment);
                    return true;
                case R.id.bottom_navigation_sos:
                    fragment = new Fragment_SOS();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

}
