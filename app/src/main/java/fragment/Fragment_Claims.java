package fragment;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cemesinsurance.cemes_customer.R;

import java.util.ArrayList;

import adapter.ClaimsRecyclerAdapter;
import model.ClaimModel;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Claims extends Fragment {
    private ClaimsRecyclerAdapter claimsAdapter;
    private RecyclerView claimsRecyclerView;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<ClaimModel> exampleClaims = new ArrayList<>();

    public Fragment_Claims() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__claims, container, false);

        claimsRecyclerView = view.findViewById(R.id.claimsRecycler);


        exampleClaims.add(new ClaimModel("Motor", "motor", "Motor accident at Thika Superhighway"));
        exampleClaims.add(new ClaimModel("Domestic", "domestic", "Robbery at 101 avenue"));
        exampleClaims.add(new ClaimModel("Domestic", "domestic", "Robbery at 101 avenue"));
        exampleClaims.add(new ClaimModel("Domestic", "domestic", "Robbery at 101 avenue"));
        exampleClaims.add(new ClaimModel("Domestic", "domestic", "Robbery at 101 avenue"));
        exampleClaims.add(new ClaimModel("Domestic", "domestic", "Robbery at 101 avenue"));
        exampleClaims.add(new ClaimModel("Domestic", "domestic", "Robbery at 101 avenue"));
        exampleClaims.add(new ClaimModel("Domestic", "domestic", "Robbery at 101 avenue"));
        exampleClaims.add(new ClaimModel("Domestic", "domestic", "Robbery at 101 avenue"));


        claimsAdapter = new ClaimsRecyclerAdapter(exampleClaims);
        layoutManager = new LinearLayoutManager(getActivity());
        claimsRecyclerView.setLayoutManager(layoutManager);
        claimsRecyclerView.setAdapter(claimsAdapter);

        // Sort an issue with the card size
        CardView cardView = view.findViewById(R.id.claimsCardView);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels - 350;
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) cardView.getLayoutParams();
        layoutParams.height = height;
        layoutParams.width = MATCH_PARENT;
        cardView.setLayoutParams(layoutParams);
        return view;
    }

}
