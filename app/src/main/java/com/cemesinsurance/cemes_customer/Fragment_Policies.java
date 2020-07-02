package com.cemesinsurance.cemes_customer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Volley.URLs;
import Volley.VolleySingleton;
import adapter.ClaimsRecyclerAdapter;
import adapter.PoliciesAdapter;
import model.ClaimModel;
import model.PolicyInterface;
import model.User;
import model.policymodels.AutoPolicyModel;
import model.policymodels.HealthPolicyModel;
import state.SharedPrefManager;

public class Fragment_Policies extends Fragment {
    RecyclerView policiesRecyclerView;
    ArrayList<PolicyInterface> policies;

    public Fragment_Policies() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__policies, container, false);
        policiesRecyclerView = view.findViewById(R.id.myPoliciesRecyclerView);
        policiesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        policies = new ArrayList<>();
        PoliciesAdapter adapter = new PoliciesAdapter(policies);

        policiesRecyclerView.setAdapter(adapter);

        getPolicies();
        return view;
    }

    public void getPolicies() {
        final User user = SharedPrefManager.getInstance(getContext()).getUser();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URLs.GET_POLICIES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            if(object.getString("message").equalsIgnoreCase("successful")) {
                                JSONArray array = object.getJSONArray("policies");
                                policies = new ArrayList<>();

                                for(int i = 0; i < array.length(); i++) {
                                    JSONObject jsonObject = array.getJSONObject(i);
                                    String policyType = jsonObject.getString("type");
                                    String underwriter = jsonObject.getString("underwriter");
                                    double cost = jsonObject.getDouble("insurance_cost");
                                    // add policies
                                    if (policyType.equalsIgnoreCase("auto")) {
                                        policies.add(new AutoPolicyModel(underwriter, cost));
                                    } else {
                                        policies.add(new HealthPolicyModel(underwriter, cost));
                                    }
                                }

                                PoliciesAdapter adapter = new PoliciesAdapter(policies);
                                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                                policiesRecyclerView.setLayoutManager(layoutManager);
                                policiesRecyclerView.setAdapter(adapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("onErrorResponse", e.getMessage());
                            Log.e("onErrorResponse", e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("onErrorResponse", error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", user.getEmail());
                return params;
            }
        };

        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }
}