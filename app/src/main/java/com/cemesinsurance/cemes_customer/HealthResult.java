package com.cemesinsurance.cemes_customer;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import Volley.URLs;
import Volley.VolleySingleton;
import adapter.AvailableHealthInsuranceAdapter;
import fragment.JubileeExtraOptionsDialogFragment;
import model.User;
import state.SharedPrefManager;

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

            @Override
            public void onGetQuoteClick() {
                final User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();

                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST,
                        URLs.ADD_CLAIM,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject object = new JSONObject(response);
                                    String message = object.getString("message");
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                    AlertDialog.Builder dialog = new AlertDialog.Builder(HealthResult.this);
                                    dialog.setMessage("One of our agents will get in touch with you")
                                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    Intent intent = new Intent(HealthResult.this, My_Wallet_07.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            })
                                            .show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("name", user.getName());
                        params.put("email", user.getEmail());
                        params.put("underwriter", "Jubilee");
                        params.put("policy_type", "medical");
                        params.put("id", String.valueOf(user.getId()));
                        return params;
                    }
                };

                VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
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
