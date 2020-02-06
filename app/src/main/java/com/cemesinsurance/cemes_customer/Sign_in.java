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

public class Sign_in extends AppCompatActivity  {

    EditText emailText;
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        setUpViews();

        MyTextView_SF_Pro_Display_Medium Login = (MyTextView_SF_Pro_Display_Medium) findViewById(R.id.login);
        //login intent opener
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


    }

    void setUpViews() {
        emailText = findViewById(R.id.logInEmailEditText);
        passwordText = findViewById(R.id.logInPasswordEditText);
    }

    void signIn() {
        final String email = emailText.getText().toString().trim().toLowerCase();
        final String password = passwordText.getText().toString().trim();

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

        if(!isNetworkAvailable()) {
            Toast.makeText(getApplicationContext(), "Please connect to the internet", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest stringRequest =  new StringRequest(
                Request.Method.POST,
                URLs.SIGN_IN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            String message = object.getString("message");
                            switch (message) {
                                case "login successful":
                                    User user = new User(
                                            object.getInt("id"),
                                            object.getString("name"),
                                            object.getString("email"),
                                            object.getString("phone")
                                    );
//                                    Log.e("json", object.toString());

                                    SharedPrefManager.getInstance(getApplicationContext()).userLogIn(user);

//                                    progressBar.setVisibility(View.GONE);
                                    Intent intent = new Intent(getApplicationContext(), My_Wallet_07.class);
                                    Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                    finish();
                                    break;

                                case "invalid password":
                                    passwordText.requestFocus();
                                    passwordText.setError("Wrong password");
                                    return;

                                case "invalid email":
                                    emailText.requestFocus();
                                    emailText.setError("Email does not exist");
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
