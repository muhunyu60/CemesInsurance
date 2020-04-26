package com.cemesinsurance.cemes_customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import adapter.AvailableHealthInsuranceAdapter;

public class HealthResult extends AppCompatActivity {

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
            AvailableHealthInsuranceAdapter adapter = new AvailableHealthInsuranceAdapter(coverLimit, applicantDOB, hasPreExistingCondition);
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
        } else {
            // TODO: If the applicant insures the family but not the spouse
        }
    }
}
