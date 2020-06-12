package com.cemesinsurance.cemes_customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import adapter.AvailableMotorInsuranceAdapter;
import customfonts.MyTextView_SF_Pro_Display_Medium;
import fragment.MotorExtraOptionsDialogFragment;
import model.AvailableMotorInsuranceModel;
import model.User;
import state.SharedPrefManager;

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

    String carRegistration;

    AvailableMotorInsuranceAdapter motorInsuranceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_result);

        String carValue = getIntent().getStringExtra("CAR_VALUE");
        // To Prevent a crash from a numberFormatException
        carValue = carValue.replaceAll(",", "");

        String carManufactureYear = getIntent().getStringExtra("CAR_MANUFACTURE_YEAR");
        String carClass = getIntent().getStringExtra("CAR_CLASS");
        String insuranceStartDate = getIntent().getStringExtra("INSURANCE_START_DATE");
        String carUse = getIntent().getStringExtra("CAR_USE");
        carRegistration = getIntent().getStringExtra("CAR_REGISTRATION");

        RecyclerView availableMotorInsurance = findViewById(R.id.availableMotorInsuranceRecycler);
        availableMotorInsurance.setLayoutManager(new LinearLayoutManager(this));
        motorInsuranceAdapter = new AvailableMotorInsuranceAdapter(
                Double.parseDouble(carValue),
                Integer.parseInt(carManufactureYear),
                carClass,
                insuranceStartDate,
                carUse
        );
        availableMotorInsurance.setAdapter(motorInsuranceAdapter);

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

        motorInsuranceAdapter.setOnItemClickListener(new AvailableMotorInsuranceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // TODO: Volley push details to the online repository
                User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
                AvailableMotorInsuranceModel selectedInsurance = motorInsuranceAdapter.availableMotorInsuranceModels.get(position);
                final String underwriter = selectedInsurance.getInsuranceName();
                final String policyType = "auto";
                final int id = user.getId();
                final String name = user.getName();
                final String email = user.getEmail();

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
                        params.put("name", name);
                        params.put("email", email);
                        params.put("underwriter", underwriter);
                        params.put("policy_type", policyType);
                        params.put("registration_number", carRegistration);
                        params.put("id", String.valueOf(id));
                        return params;
                    }
                };

                VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
            }
        });
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
        roadRescueValue = roadRescue ? "yes" : "no";
        setTextViewValues();

        motorInsuranceAdapter.setExtras(
                Double.parseDouble(windscreenValue),
                Double.parseDouble(radioValue),
                excessProtector,
                politicalViolenceAndTerrorism,
                lossOfUse,
                roadRescue
        );
    }


}
