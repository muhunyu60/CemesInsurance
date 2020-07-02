package com.cemesinsurance.cemes_customer;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import fragment.Fragment_Home;
import fragment.Fragment_Claims;
import fragment.Fragment_SOS;
import fragment.Profile_Fragment;
import fragment.Settings_Fragment;

public class My_Wallet_07 extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    NavigationView drawerNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_07);

        //Load the Home Page by default
        loadFragment(new Fragment_Home());

        //set up bottom navigation view
        final BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        //Add behaviour
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehaviour());

        //Drawer Layout
        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);

        drawerNavigation = findViewById(R.id.drawer_nav);
        drawerNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                drawerNavigation.setCheckedItem(itemId);

                switch(itemId) {
                    case R.id.drawer_home:
                        loadFragment(new Fragment_Home());
                        navigationView.animate()
                                .translationY(0)
                                .setDuration(200);
                        break;
                    case R.id.drawer_profile:
                        loadFragment(new Profile_Fragment());
                        navigationView.animate()
                                .translationY(navigationView.getHeight())
                                .setDuration(200);
                        break;
                    case R.id.drawer_settings:
                        loadFragment(new Settings_Fragment());
                        navigationView.animate()
                                .translationY(navigationView.getHeight())
                                .setDuration(200);
                        break;
                    case R.id.drawer_policies:
                        loadFragment(new Fragment_Policies());
                        navigationView.animate()
                                .translationY(navigationView.getHeight())
                                .setDuration(200);
                }

                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        super.onBackPressed();
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
