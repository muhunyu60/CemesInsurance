package com.cemesinsurance.cemes_customer;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import Volley.URLs;
import Volley.VolleySingleton;
import customfonts.MyTextView_SF_Pro_Display_Medium;
import fragment.NumberPickerDialogFragment;



public class Fragment_Motor_Insurance extends Fragment implements AdapterView.OnItemSelectedListener, NumberPicker.OnValueChangeListener {

    EditText yearDatePicker;
    EditText startDatePicker;
    EditText registrationEditText;
    EditText carValueEditText;
    Spinner carUseSpinner;
    Spinner carClassSpinner;
    Spinner carModelSpinner;
    Spinner carMakeSpinner;
    TextView registrationText;
    ConstraintLayout constraintLayout;
    ArrayList<String> carMakes = new ArrayList<>();
    Map<String, ArrayList<String>> cars = new HashMap<>();
    MyTextView_SF_Pro_Display_Medium getQuoteBtn;


    String[] commercialUses = new String[]{
            "Select Car Use",
            "General Cartage",
            "Own Goods",
            "School Buses",
            "Tankers",
            "Tractors",
            "Single Cabin Pick Up"
        };

    String[] psvUses = new String[] {
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
        registrationEditText = view.findViewById(R.id.registrationEditText);
        carValueEditText = view.findViewById(R.id.carValueEditText);
        constraintLayout = view.findViewById(R.id.motorConstraintLayout);
        startDatePicker = view.findViewById(R.id.startDateDatePicker);
        getQuoteBtn = view.findViewById(R.id.get_quote);

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
                                String dateString = i + "/" + i1 + "/" + i2;
                                startDatePicker.setText(dateString);
                            }
                        },
                        year,
                        month,
                        day);
                dp.getDatePicker().setMinDate(calendar.getTimeInMillis());
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

        hideCarUse();
        getCarMakesNetworkRequest(view.getContext());
        getCarModelsNetworkRequest(view.getContext());

        getQuoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getQuote();
            }
        });

        return view;
    }

    private void getQuote() {
        //Basic Authentication
        if(carClassSpinner.getSelectedItem().toString().equalsIgnoreCase("Select a Car Class")) {
            carClassSpinner.requestFocus();
            Toast.makeText(getActivity(), "Please select a car class", Toast.LENGTH_SHORT).show();
            return;
        }

        if(carUseSpinner.getVisibility() == View.VISIBLE) {
            if(carUseSpinner.getSelectedItem().toString().equalsIgnoreCase("Select Car Use")) {
                carUseSpinner.requestFocus();
                Toast.makeText(getActivity(), "Please select a car Use", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        if(registrationEditText.getText().toString().trim().equals("")) {
            registrationEditText.requestFocus();
            registrationEditText.setError("Please fill in the registration number");
            return;
        }

        if(carValueEditText.getText().toString().trim().equals("")) {
            carValueEditText.requestFocus();
            carValueEditText.setError("Please fill in the value of the car");
            return;
        }

        if(carMakeSpinner.getSelectedItem().toString().equalsIgnoreCase("Select Car Make")) {
            carMakeSpinner.requestFocus();
            Toast.makeText(getActivity(), "Please select a car Make", Toast.LENGTH_SHORT).show();
            return;
        }

        if(carModelSpinner.getSelectedItem().toString().equalsIgnoreCase("Select a Car Model")) {
            carModelSpinner.requestFocus();
            Toast.makeText(getActivity(), "Please select a car Make", Toast.LENGTH_SHORT).show();
            return;
        }

        if(yearDatePicker.getText().toString().trim().equals("")) {
            yearDatePicker.setError("Please fill in the year of manufacture");
            return;
        }

        if(startDatePicker.getText().toString().trim().equals("")) {
            startDatePicker.setError("Please fill in the start date");
            return;
        }

        String carClass = carClassSpinner.getSelectedItem().toString();
        String carUse = carUseSpinner.getVisibility() == View.VISIBLE ? carUseSpinner.getSelectedItem().toString() : " ";
        String registration = registrationEditText.getText().toString();
        String carValue = carValueEditText.getText().toString();
        String manufactureYear = yearDatePicker.getText().toString();
        String startYear = startDatePicker.getText().toString();

        Intent intent = new Intent(getActivity(), MotorResultActivity.class);

        intent.putExtra("CAR_CLASS", carClass);
        intent.putExtra("CAR_USE", carUse);
        intent.putExtra("CAR_REGISTRATION", registration);
        intent.putExtra("CAR_VALUE", carValue);
        intent.putExtra("CAR_MANUFACTURE_YEAR", manufactureYear);
        intent.putExtra("INSURANCE_START_YEAR", startYear);

        startActivity(intent);
    }

    public void populateCarMakesSpinner(JSONArray array, final Context context) {
        carMakes.add("Select Car Make");
        for(int i = 0; i < array.length(); i++) {
            try {
                carMakes.add(array.getJSONObject(i).getString("car_make"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ArrayAdapter<String> carMakeAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, carMakes);
        carMakeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carMakeSpinner.setAdapter(carMakeAdapter);

    }

    public void getCarMakesNetworkRequest(final Context context) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                URLs.CAR_MAKES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("car_makes");
                            populateCarMakesSpinner(jsonArray, context);

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
        );
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void getCarModelsNetworkRequest(final Context context) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                URLs.CAR_MODELS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("car_makes");
                            populateCarsHashMap(array, context);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } // OnResponse
                }, // Response Listener
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    } // OnErrorResponse
                } // Response.ErrorListener

        ); // StringRequest

        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    private void populateCarsHashMap(JSONArray array, final Context context) {
        String current = "Select Car Make";
        ArrayList<String> temp = new ArrayList<>();
        temp.add("Select a Car Model");

        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject object = array.getJSONObject(i);
                if(object.getString("car_make").equals(current)) {
                    temp.add(object.getString("model"));
                } else {
                    cars.put(current, temp);

                    temp = new ArrayList<>();
                    current = object.getString("car_make");
                    temp.add(object.getString("model"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //To add the last element
        cars.put(current, temp);

        ArrayAdapter<String> carMakeAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, carMakes);
        carMakeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carMakeSpinner.setAdapter(carMakeAdapter);
        carMakeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedCarMake = adapterView.getItemAtPosition(i).toString();
                ArrayList<String> models = cars.get(selectedCarMake);
                ArrayAdapter<String> carModelAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, models);
                carModelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                try {
                    carModelSpinner.setAdapter(carModelAdapter);
                } catch (NullPointerException e) {
                    Log.e("selectedCarMake:", selectedCarMake);
                    Log.e("ArraySize", String.valueOf(models.size()));
                    Log.e("error:", e.toString());
                } catch (IllegalArgumentException e) {
                    Log.e("error:", e.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
                ArrayAdapter<String> carUseAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, commercialUses);
                carUseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                carUseSpinner.setAdapter(carUseAdapter);

            } else if(selectedCarClass.equalsIgnoreCase("PSV")) {
                ArrayAdapter<String> carUseAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, psvUses);
                carUseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                carUseSpinner.setAdapter(carUseAdapter);
            } else {
                String[] selectUse = new String[1];
                selectUse[0] = "Select Car Use";
                ArrayAdapter<String> carUseAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, selectUse);
                carUseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                carUseSpinner.setAdapter(carUseAdapter);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        yearDatePicker.setText(String.valueOf(numberPicker.getValue()));
    }

    public void showNumberPicker() {
        NumberPickerDialogFragment newFragment = new NumberPickerDialogFragment();
        newFragment.setValueChangeListener(this);
        newFragment.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), "time picker");
    }
}
