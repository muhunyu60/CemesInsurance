package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.cemesinsurance.cemes_customer.R;

import model.User;
import state.SharedPrefManager;

public class Profile_Fragment extends Fragment {

    private EditText nameEditText;
    private EditText phoneEditText;
    private EditText emailEditText;
    private User user;

    public Profile_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialise variables
        nameEditText =  view.findViewById(R.id.profileNameEditText);
        phoneEditText = view.findViewById(R.id.profilePhoneEditText);
        emailEditText = view.findViewById(R.id.profileEmailEditText);
        user = SharedPrefManager.getInstance(getActivity()).getUser();

        // Set the editText values based on the current logged in user credentials
        nameEditText.setText(user.getName());
        phoneEditText.setText(user.getPhone());
        emailEditText.setText(user.getEmail());

        return view;
    }

}
