package com.cemesinsurance.cemes_customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import adapter.AvailableHealthInsuranceAdapter;
import fragment.JubileeExtraOptionsDialogFragment;

public class HealthResult extends AppCompatActivity implements JubileeExtraOptionsDialogFragment.HealthExtraOptionsOnClickListener {
    AvailableHealthInsuranceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_result);

        RecyclerView recyclerView = findViewById(R.id.health_recycler_view);
        if(!getIntent().getBooleanExtra("INSURE_FAMILY", false)) {
            // If the applicant chooses the single option

            // Get data passed by the intent from fragment_health_insurance
            String coverLimit = getIntent().getStringExtra("COVER_LIMIT");
            String applicantDOB = getIntent().getStringExtra("APPLICANT_DOB");
            Boolean hasPreExistingCondition = getIntent().getBooleanExtra("HAS_PREEXISTING_CONDITION", false);

            // Set up the recycler view
            adapter = new AvailableHealthInsuranceAdapter(coverLimit, applicantDOB, hasPreExistingCondition);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        } else if(getIntent().getBooleanExtra("INSURE_SPOUSE", false)) {
            // If the applicant insures the spouse

            // Get data passed by the intent from fragment_health_insurance
            String coverLimit = getIntent().getStringExtra("COVER_LIMIT");
            String applicantDOB = getIntent().getStringExtra("APPLICANT_DOB");
            String spouseDOB = getIntent().getStringExtra("SPOUSE_DOB");
            String numberOfChildren = getIntent().getStringExtra("NUMBER_OF_CHILDREN");
            Boolean hasPreExistingCondition = getIntent().getBooleanExtra("HAS_PREEXISTING_CONDITION", false);

            adapter = new AvailableHealthInsuranceAdapter(coverLimit, applicantDOB, spouseDOB, Integer.parseInt(numberOfChildren), hasPreExistingCondition);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        } else {
            String coverLimit = getIntent().getStringExtra("COVER_LIMIT");
            String applicantDOB = getIntent().getStringExtra("APPLICANT_DOB");
            String numberOfChildren = getIntent().getStringExtra("NUMBER_OF_CHILDREN");
            Boolean hasPreExistingCondition = getIntent().getBooleanExtra("HAS_PREEXISTING_CONDITION", false);

            adapter = new AvailableHealthInsuranceAdapter(coverLimit, applicantDOB, Integer.parseInt(numberOfChildren), hasPreExistingCondition);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }

        adapter.setOnItemClickListener(new AvailableHealthInsuranceAdapter.onItemClickListener() {
            @Override
            public void onItemClick() {
                showDialog();
            }
        });
    }

    public void showDialog() {
        JubileeExtraOptionsDialogFragment dialogFragment = new JubileeExtraOptionsDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "Jubilee Extra Options");
    }

    @Override
    public void applyExtras(Boolean maternity, Boolean dental, Boolean optical, Boolean personalAccident, Boolean outpatient) {
        adapter.setExtras(maternity, dental, optical, personalAccident, outpatient);
    }
}
