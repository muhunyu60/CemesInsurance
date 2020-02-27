package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.cemesinsurance.cemes_customer.R;


public class Fragment_Travel_Insurance extends Fragment {
    private Spinner destinationSpinner;
    private Spinner travelPurposeSpinner;
    private Spinner coverTypeSpinner;

    public Fragment_Travel_Insurance() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_travel_insurance, container, false);

        // Initialise the views
        destinationSpinner = view.findViewById(R.id.destinationSpinner);
        travelPurposeSpinner = view.findViewById(R.id.travelPurposeSpinner);
        coverTypeSpinner = view.findViewById(R.id.coverTypeSpinner);

        // Adapters for each of the spinner
        ArrayAdapter<CharSequence> destinationsAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.travel_destination, android.R.layout.simple_spinner_item);
        destinationsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destinationSpinner.setAdapter(destinationsAdapter);

        ArrayAdapter<CharSequence> travelPurposeAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.travel_purpose, android.R.layout.simple_spinner_item);
        travelPurposeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        travelPurposeSpinner.setAdapter(travelPurposeAdapter);

        ArrayAdapter<CharSequence> coverTypeAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.cover_type, android.R.layout.simple_spinner_item);
        coverTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coverTypeSpinner.setAdapter(coverTypeAdapter);
        return view;
    }
}
