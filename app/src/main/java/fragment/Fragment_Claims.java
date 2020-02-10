package fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cemesinsurance.cemes_customer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Claims extends Fragment {


    public Fragment_Claims() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__claims, container, false);
    }

}
