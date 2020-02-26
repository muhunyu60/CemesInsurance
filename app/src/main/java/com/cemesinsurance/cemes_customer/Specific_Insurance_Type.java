package com.cemesinsurance.cemes_customer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fragment.Fragment_Domestic_Insurance;
import fragment.Fragment_Medical_Insurance;

public class Specific_Insurance_Type extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific__insurance__type);

        // Load fragment based on clicked image
        String fragment = getIntent().getStringExtra("fragment");
        switch (fragment) {
            case "medical":
                loadFragment(new Fragment_Medical_Insurance());
                break;
            case "domestic":
                loadFragment(new Fragment_Domestic_Insurance());
                break;
            default:
                loadFragment(new Fragment_Motor_Insurance());
        }

    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.insurance_type_placeholder, fragment);
        fragmentTransaction.commit();
    }
}
