package fragment;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.cemesinsurance.cemes_customer.R;

import java.util.Calendar;

public class Fragment_Medical_Insurance extends Fragment {
    RadioGroup applicantsRadioGroup;
    Spinner coverLimitSpinner;
    EditText applicantDateOfBirthEditText;
    RadioGroup existingConditionRadioGroup;
    RadioGroup insureSpouseRadioGroup;
    EditText spouseDateOfBirthEditText;
    EditText numberOfChildrenEditText;
    TextView spouseDateOfBirthTextView;
    TextView insureSpouseTextView;
    TextView numberOfChildrenTextView;
    RadioButton yesInsureSpouseRadioButton;
    RadioButton noInsureSpouseRadioButton;
    RadioButton familyRadioButton;
    RadioButton singleRadioButton;

    public Fragment_Medical_Insurance() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__medical__insurance, container, false);

        // Initialise Views
        applicantsRadioGroup = view.findViewById(R.id.applicantsRadioGrp);
        coverLimitSpinner = view.findViewById(R.id.coverLimitSpinner);
        applicantDateOfBirthEditText = view.findViewById(R.id.applicantDateOfBirthEditText);
        existingConditionRadioGroup = view.findViewById(R.id.conditionRadioGrp);
        insureSpouseRadioGroup = view.findViewById(R.id.insureSpouseRadioGrp);
        spouseDateOfBirthEditText = view.findViewById(R.id.spouseDateOfBirthEditText);
        numberOfChildrenEditText = view.findViewById(R.id.numberOfChildrenEditText);
        numberOfChildrenTextView = view.findViewById(R.id.numberOfChildrenTextView);
        spouseDateOfBirthTextView = view.findViewById(R.id.spouseDateOfBirthTextView);
        insureSpouseTextView = view.findViewById(R.id.insureSpouseTextView);
        yesInsureSpouseRadioButton = view.findViewById(R.id.yesInsureSpouseRadioBtn);
        noInsureSpouseRadioButton = view.findViewById(R.id.noInsureSpouseRadioBtn);
        familyRadioButton = view.findViewById(R.id.familyRadioBtn);
        singleRadioButton = view.findViewById(R.id.singleRadioBtn);

        // Set up Spinner
        ArrayAdapter<CharSequence>  coverLimitsAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.cover_limits, android.R.layout.simple_spinner_item);
        coverLimitsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coverLimitSpinner.setAdapter(coverLimitsAdapter);

        // Set the onClickListeners for the various radio buttons
        yesInsureSpouseRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSpouseOptions();
            }
        });
        noInsureSpouseRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFamilyOptionsWithoutSpouse();
            }
        });
        familyRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (yesInsureSpouseRadioButton.isChecked()) {
                    showSpouseOptions();
                } else {
                    showFamilyOptionsWithoutSpouse();
                }
            }
        });
        singleRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOnlySingleOptions();
            }
        });

        // Set Date Picker Dialogs for the different dates
        spouseDateOfBirthEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR) - 18;
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                calendar.set(year, month, day);
                ;

                if (!TextUtils.isEmpty(spouseDateOfBirthEditText.getText().toString())) {
                    String selectedDate = spouseDateOfBirthEditText.getText().toString();
                    String[] date = selectedDate.split("/");

                    String sYear = date[0].trim();
                    String sMonth = date[1].trim();
                    String sDay = date[2].trim();

                    year = Integer.parseInt(sYear);
                    month = Integer.parseInt(sMonth) - 1;
                    day = Integer.parseInt(sDay);

                }

                DatePickerDialog dp = new DatePickerDialog(
                        view.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                i1 += 1;
                                String dateString = i + "/" + i1 + "/" + i2;
                                spouseDateOfBirthEditText.setText(dateString);
                            }
                        },
                        year,
                        month,
                        day);
                dp.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                dp.show();
            }
        });
        applicantDateOfBirthEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR) - 18;
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                calendar.set(year, month, day);
                ;

                if (!TextUtils.isEmpty(applicantDateOfBirthEditText.getText().toString())) {
                    String selectedDate = applicantDateOfBirthEditText.getText().toString();
                    String[] date = selectedDate.split("/");

                    String sYear = date[0].trim();
                    String sMonth = date[1].trim();
                    String sDay = date[2].trim();

                    year = Integer.parseInt(sYear);
                    month = Integer.parseInt(sMonth) - 1;
                    day = Integer.parseInt(sDay);

                }

                DatePickerDialog dp = new DatePickerDialog(
                        view.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                i1 += 1;
                                String dateString = i + "/" + i1 + "/" + i2;
                                applicantDateOfBirthEditText.setText(dateString);
                            }
                        },
                        year,
                        month,
                        day);
                dp.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                dp.show();
            }
        });

        // Initially only the options for a single applicant will be visible
        showOnlySingleOptions();
        return view;
    }

    public void showOnlySingleOptions() {
        spouseDateOfBirthTextView.setVisibility(View.GONE);
        spouseDateOfBirthEditText.setVisibility(View.GONE);
        insureSpouseTextView.setVisibility(View.GONE);
        insureSpouseRadioGroup.setVisibility(View.GONE);
        numberOfChildrenEditText.setVisibility(View.GONE);
        numberOfChildrenTextView.setVisibility(View.GONE);
    }

    public void showFamilyOptionsWithoutSpouse() {
        showOnlySingleOptions();
        insureSpouseTextView.setVisibility(View.VISIBLE);
        insureSpouseRadioGroup.setVisibility(View.VISIBLE);
        numberOfChildrenEditText.setVisibility(View.VISIBLE);
        numberOfChildrenTextView.setVisibility(View.VISIBLE);
    }

    public void showSpouseOptions() {
        showFamilyOptionsWithoutSpouse();
        spouseDateOfBirthTextView.setVisibility(View.VISIBLE);
        spouseDateOfBirthEditText.setVisibility(View.VISIBLE);
    }
}
