package fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cemesinsurance.cemes_customer.Add_Claim;
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
    private FloatingActionButton floatingActionButton;

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
        floatingActionButton = view.findViewById(R.id.add_claim_fab);


        exampleClaims.add(new ClaimModel("Motor", "motor", "Motor accident at Thika Superhighway"));
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
        int height = displayMetrics.heightPixels - 400;
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) cardView.getLayoutParams();
        layoutParams.height = height;
        layoutParams.width = MATCH_PARENT;
        cardView.setLayoutParams(layoutParams);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Add_Claim.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
