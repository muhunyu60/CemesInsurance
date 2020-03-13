package com.cemesinsurance.cemes_customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import adapter.AvailableMotorInsuranceAdapter;

public class MotorResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_result);

        RecyclerView availableMotorInsurance = findViewById(R.id.availableMotorInsuranceRecycler);
        availableMotorInsurance.setLayoutManager(new LinearLayoutManager(this));
        availableMotorInsurance.setAdapter(new AvailableMotorInsuranceAdapter());
    }
}
