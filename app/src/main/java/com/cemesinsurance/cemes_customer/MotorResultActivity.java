package com.cemesinsurance.cemes_customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import adapter.AvailableMotorInsuranceAdapter;
import customfonts.MyTextView_SF_Pro_Display_Medium;
import fragment.MotorExtraOptionsDialogFragment;

public class MotorResultActivity extends AppCompatActivity implements MotorExtraOptionsDialogFragment.MotorExtraOptionsDialogListener {
    CardView extraOptionsCardView;

    MyTextView_SF_Pro_Display_Medium windScreenValueTextView;
    MyTextView_SF_Pro_Display_Medium PVTValueTextView;
    MyTextView_SF_Pro_Display_Medium radioValueTextView;
    MyTextView_SF_Pro_Display_Medium excessProtectorValueTextView;
    MyTextView_SF_Pro_Display_Medium roadRescueValueTextView;
    MyTextView_SF_Pro_Display_Medium lossOfUseValueTextView;

    String windScreenValue = "0";
    String PVTValue = "0";
    String radioValue = "0";
    String excessProtectorValue = "0";
    String roadRescueValue = "0";
    String lossOfUseValue = "0";

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

        windScreenValueTextView = findViewById(R.id.windscreenExampleValueTextView);
        PVTValueTextView = findViewById(R.id.pvtExampleValueTextView);
        radioValueTextView = findViewById(R.id.radioExampleValueTextView);
        excessProtectorValueTextView = findViewById(R.id.excessProtectorExampleValueTextView);
        roadRescueValueTextView = findViewById(R.id.roadRescueExampleValueTextView);
        lossOfUseValueTextView = findViewById(R.id.lossOfUseExampleValueTextView);

        setTextViewValues();
    }

    public void showExtraOptionsDialogFragment() {
        MotorExtraOptionsDialogFragment dialogFragment = new MotorExtraOptionsDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "Extra Motor Options");
    }

    public void setTextViewValues() {
        windScreenValueTextView.setText(windScreenValue);
        PVTValueTextView.setText(PVTValue);
        radioValueTextView.setText(radioValue);
        excessProtectorValueTextView.setText(excessProtectorValue);
        roadRescueValueTextView.setText(roadRescueValue);
        lossOfUseValueTextView.setText(lossOfUseValue);
    }

    @Override
    public void passOptions(String windscreenValue, String radioValue, Boolean excessProtector, Boolean politicalViolenceAndTerrorism, Boolean lossOfUse, Boolean roadRescue) {
        this.windScreenValue = windscreenValue;
        this.radioValue = radioValue;
        excessProtectorValue = excessProtector ? "yes" : "no";
        PVTValue = politicalViolenceAndTerrorism ? "yes" : "no";
        lossOfUseValue = lossOfUse ? "yes" : "no";
        roadRescueValue = lossOfUse ? "yes" : "no";
        setTextViewValues();
    }
}
