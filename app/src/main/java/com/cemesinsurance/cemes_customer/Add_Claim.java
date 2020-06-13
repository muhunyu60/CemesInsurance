package com.cemesinsurance.cemes_customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import customfonts.MyTextView_SF_Pro_Display_Medium;
import model.User;
import state.SharedPrefManager;

public class Add_Claim extends AppCompatActivity {
    Spinner insuranceTypeSpinner;
    EditText description;
    MyTextView_SF_Pro_Display_Medium getQuote;
    Button TakePictureButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__claim);

        description = findViewById(R.id.claimDescriptionEditText);
        TakePictureButton = findViewById(R.id.btn_get_pic);

        ArrayAdapter<CharSequence>  claimTypeAdapter = ArrayAdapter.createFromResource(this, R.array.claim_cover_type, android.R.layout.simple_spinner_item);
        claimTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        insuranceTypeSpinner = findViewById(R.id.claimTypeSpinner);
        insuranceTypeSpinner.setAdapter(claimTypeAdapter);

        getQuote = findViewById(R.id.get_quote);
        getQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addClaim();
            }
        });
    }

    public void addClaim() {
        if(insuranceTypeSpinner.getSelectedItem().toString().equalsIgnoreCase("Select Your Insurance Type")) {
            Toast.makeText(getApplicationContext(), "Please Select Your Insurance Type", Toast.LENGTH_SHORT).show();
            return;
        }

        if(description.getText().toString().trim().equals("")) {
            description.requestFocus();
            description.setError("Enter a description");
            return;
        }
        final User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        final String claimType = insuranceTypeSpinner.getSelectedItem().toString();
        final String descriptionText = description.getText().toString();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URLs.ADD_CLAIM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            String message = object.getString("message");
                            if(message.equalsIgnoreCase("successful")) {
                                Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "An error occured on our side", Toast.LENGTH_SHORT).show();
                                Log.e("makeClaim", message);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        finish();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "An error occured on our side", Toast.LENGTH_SHORT).show();
                        Log.e("makeClaim", error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", user.getEmail());
                params.put("description", descriptionText);
                params.put("name", user.getName());
                params.put("phone", user.getPhone());
                params.put("claim_type", claimType);
                return params;
            }
        };

        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
}
