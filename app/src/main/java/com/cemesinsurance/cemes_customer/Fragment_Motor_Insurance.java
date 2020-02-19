package com.cemesinsurance.cemes_customer;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Volley.URLs;
import Volley.VolleySingleton;
import fragment.NumberPickerDialogFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Motor_Insurance extends Fragment implements AdapterView.OnItemSelectedListener, NumberPicker.OnValueChangeListener {

    EditText yearDatePicker;
    EditText startDatePicker;
    Spinner carUseSpinner;
    Spinner carClassSpinner;
    Spinner carModelSpinner;
    Spinner carMakeSpinner;
    TextView registrationText;
    ConstraintLayout constraintLayout;
    Map<String, List<String>> cars = new HashMap<>();

    String commercialUses[] = new String[]{
            "Select Car Use",
            "General Cartage",
            "Own Goods",
            "School Buses",
            "Tankers",
            "Tractors",
            "Single Cabin Pick Up"
        };

    String psvUses[] = new String[] {
            "Select Car Use",
            "Chauffer Driven",
            "Self Driven",
            "Matatus"
        };

    public Fragment_Motor_Insurance() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_motor_insurance, container, false);

        // Set up views
        carUseSpinner = view.findViewById(R.id.carUseSpinner);
        carClassSpinner = view.findViewById(R.id.carClassSpinner);
        carModelSpinner = view.findViewById(R.id.carModelSpinner);
        carMakeSpinner = view.findViewById(R.id.carMakeSpinner);
        registrationText = view.findViewById(R.id.registrationText);
        constraintLayout = view.findViewById(R.id.motorConstraintLayout);
        startDatePicker = view.findViewById(R.id.startDateDatePicker);

        startDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                if(!TextUtils.isEmpty(startDatePicker.getText().toString())) {
                    String selectedDate = startDatePicker.getText().toString();
                    String[] date = selectedDate.split("/");

                    String sYear = date[0].trim();
                    String sMonth = date[1].trim();
                    String sDay = date[2].trim();

                    year = Integer.parseInt(sYear);
                    month = Integer.parseInt(sMonth);
                    day = Integer.parseInt(sDay);

                }

                DatePickerDialog dp = new DatePickerDialog(
                        view.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                i1 += 1;
                                startDatePicker.setText(String.valueOf(i) + "/" + String.valueOf(i1) + "/" + String.valueOf(i2));
                            }
                        },
                        year,
                        month,
                        day);
                dp.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                dp.show();

            }
        });

        yearDatePicker = view.findViewById(R.id.yearDatePicker);
        yearDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNumberPicker();
            }
        });



        // Set up Spinners
        carClassSpinner.setPrompt("Enter Car Class");
        ArrayAdapter<CharSequence>  carClassAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.car_classes, android.R.layout.simple_spinner_item);
        carClassAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carClassSpinner.setAdapter(carClassAdapter);
        carClassSpinner.setOnItemSelectedListener(this);

        List<String> initialList = new ArrayList<String>();
        initialList.add("Enter Car Model");
        cars.put("Enter Car Class", initialList);
        hideCarUse();
//        getCars(view.getContext());

        return view;
    }

    public void getCars(final Context context) {
        StringRequest stringRequest =  new StringRequest(
                Request.Method.GET,
                URLs.CARS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);

                            while (object.keys().hasNext()) {
                                String key = object.keys().next();
                                List models = toList(object.getJSONArray(key));
                                cars.put(key, models);

                                String[] carMakes = cars.keySet().toArray(new String[cars.size()]);

                                ArrayAdapter carMakeAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, carMakes);
                                carMakeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                carMakeSpinner.setAdapter(carMakeAdapter);
                            }

                        } catch (JSONException exception) {
                            Log.e("JSONerror", exception.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("volleyError", "Network connection problem");
                    }
                }
        );

        int socketTimeout = 30000;

        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);

        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void hideCarUse() {
        carUseSpinner.setVisibility(View.GONE);
    }

    public void showCarUse() {
        carUseSpinner.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedCarClass = adapterView.getItemAtPosition(i).toString();
        if (selectedCarClass.equalsIgnoreCase("private")) {
            hideCarUse();
        } else {
            showCarUse();
            if(selectedCarClass.equalsIgnoreCase("commercial")) {
                ArrayAdapter carUseAdapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, commercialUses);
                carUseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                carUseSpinner.setAdapter(carUseAdapter);

            } else if(selectedCarClass.equalsIgnoreCase("PSV")) {
                ArrayAdapter carUseAdapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, psvUses);
                carUseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                carUseSpinner.setAdapter(carUseAdapter);
            } else {
                String[] selectUse = new String[1];
                selectUse[0] = "Select Car Use";
                ArrayAdapter carUseAdapter = new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, selectUse);
                carUseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                carUseSpinner.setAdapter(carUseAdapter);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public List toList(JSONArray array) {
        List<String> list = new ArrayList<String>();
        try {
            for(int i = 0; i < array.length(); i++) {
                list.add(array.getJSONObject(i).getString("model"));
            }
        } catch (JSONException exception) {
            Log.e("JSONException", exception.toString());
            list.add(exception.toString());
        }
        return list;
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        yearDatePicker.setText(String.valueOf(numberPicker.getValue()));
    }

    public void showNumberPicker() {
        NumberPickerDialogFragment newFragment = new NumberPickerDialogFragment();
        newFragment.setValueChangeListener(this);
        newFragment.show(getActivity().getSupportFragmentManager(), "time picker");
    }
}
