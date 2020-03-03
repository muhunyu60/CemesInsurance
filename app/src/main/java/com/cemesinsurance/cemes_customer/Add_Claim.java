package com.cemesinsurance.cemes_customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Add_Claim extends AppCompatActivity {
    Spinner insuranceTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__claim);

        ArrayAdapter<CharSequence>  claimTypeAdapter = ArrayAdapter.createFromResource(this, R.array.claim_cover_type, android.R.layout.simple_spinner_item);
        claimTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        insuranceTypeSpinner = findViewById(R.id.claimTypeSpinner);
        insuranceTypeSpinner.setAdapter(claimTypeAdapter);
    }
}
