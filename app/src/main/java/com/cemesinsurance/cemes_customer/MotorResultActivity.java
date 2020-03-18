package com.cemesinsurance.cemes_customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import adapter.AvailableMotorInsuranceAdapter;
import fragment.MotorExtraOptionsDialogFragment;

public class MotorResultActivity extends AppCompatActivity {
    CardView extraOptionsCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_result);

        RecyclerView availableMotorInsurance = findViewById(R.id.availableMotorInsuranceRecycler);
        availableMotorInsurance.setLayoutManager(new LinearLayoutManager(this));
        availableMotorInsurance.setAdapter(new AvailableMotorInsuranceAdapter());

        extraOptionsCardView = findViewById(R.id.additionalOptionsCardView);
        extraOptionsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showExtraOptionsDialogFragment();
            }
        });
    }

    public void showExtraOptionsDialogFragment() {
        MotorExtraOptionsDialogFragment dialogFragment = new MotorExtraOptionsDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "Extra Motor Options");
    }
}
