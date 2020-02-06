package com.cemesinsurance.cemes_customer;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class Login extends AppCompatActivity  {
    private EditText emailText;
    private EditText nameText;
    private EditText phoneText;
    private EditText passwordText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpViews();

        MyTextView_SF_Pro_Display_Medium Login = (MyTextView_SF_Pro_Display_Medium) findViewById(R.id.login);
        //login intent opener
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });


    }

    private void setUpViews() {
        emailText = findViewById(R.id.signUpEmailEditText);
        nameText = findViewById(R.id.signUpNameEditText);
        passwordText = findViewById(R.id.signUpPasswordEditText);
        phoneText = findViewById(R.id.signUpPhoneEditText);
    }

    public void signUp() {
        final String email = emailText.getText().toString().trim().toLowerCase();
        final String password = passwordText.getText().toString().trim();
        final String phone = phoneText.getText().toString().trim();
        final String name = nameText.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            emailText.requestFocus();
            emailText.setError("Please fill in your email");
            return;
        }

        if(TextUtils.isEmpty(password)) {
            passwordText.requestFocus();
            passwordText.setError("Please fill in your password");
            return;
        }

        if(TextUtils.isEmpty(phone)) {
            phoneText.requestFocus();
            phoneText.setError("Please fill in your phone number");
            return;
        }

        if(TextUtils.isEmpty(name)) {
            nameText.requestFocus();
            nameText.setError("Please fill in your Names");
            return;
        }

        if(!isNetworkAvailable()) {
            Toast.makeText(getApplicationContext(), "Please connect to the internet", Toast.LENGTH_SHORT).show();
            return;
        }

//        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest =  new StringRequest(
                Request.Method.POST,
                URLs.SIGN_UP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            String message = object.getString("message");
                            switch (message) {
                                case "sign up successful":
                                    User user = new User(
                                            object.getInt("id"),
                                            object.getString("name"),
                                            object.getString("email"),
                                            object.getString("phone")
                                    );
                                    Log.e("json", object.toString());

                                    SharedPrefManager.getInstance(getApplicationContext()).userLogIn(user);

//                                    progressBar.setVisibility(View.GONE);
                                    Intent intent = new Intent(getApplicationContext(), My_Wallet_07.class);
                                    Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                    break;

                                case "user exists":
                                    emailText.requestFocus();
                                    emailText.setError("Email already exists");
                                    return;

                                default:
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                    Log.e("status", message);
//                                    progressBar.setVisibility(View.GONE);
                            }
                        } catch (JSONException exception) {
//                            progressBar.setVisibility(View.GONE);
                            exception.printStackTrace();
                            Log.e("JSONException", exception.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("NetworkError: ", error.toString());
//                        progressBar.setVisibility(View.GONE);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                params.put("phone", phone);
                params.put("name", name);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}
