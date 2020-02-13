package com.cemesinsurance.cemes_customer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Motor_Insurance extends Fragment {

    Spinner carUseSpinner;
    Spinner carClassSpinner;
    Spinner carModelSpinner;
    Spinner carMakeSpinner;

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

        // Adapters for the spinners
        ArrayAdapter<CharSequence>  carClassAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.car_classes, android.R.layout.simple_spinner_item);
        carClassAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carClassSpinner.setAdapter(carClassAdapter);
        return view;
    }

}
