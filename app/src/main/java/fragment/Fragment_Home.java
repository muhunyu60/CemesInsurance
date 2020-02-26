package fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cemesinsurance.cemes_customer.Login_01;
import com.cemesinsurance.cemes_customer.My_Wallet_07;
import com.cemesinsurance.cemes_customer.R;
import com.cemesinsurance.cemes_customer.Specific_Insurance_Type;

import java.util.ArrayList;

import adapter.View_Pager_Adapter_my_saving;
import adapter.WalletAdapter1;
import adapter.WalletAdapter2;
import me.relex.circleindicator.CircleIndicator;
import model.User;
import model.WalletModel;
import model.WalletModel2;
import state.SharedPrefManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Home extends Fragment {

    private TextView nameText;
    private TextView emailText;

    private WalletAdapter1 walletAdapter1;
    private RecyclerView recyclerview;
    private ArrayList<WalletModel> walletModelArrayList;

    private WalletAdapter2 walletAdapter2;
    private RecyclerView recyclerview1;
    private ArrayList<WalletModel2> walletModel2ArrayList;

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private View_Pager_Adapter_my_saving view_pager_adapter_my_saving;


    Integer oval[] = {
            R.drawable.ic_group_108,
            R.drawable.ic_group_109,
            R.drawable.ic_group_110,
            R.drawable.ic_group_111,
            R.drawable.ic_group_112,
            R.drawable.ic_group_113
    };
    String txtshopping[] = {
            "E-Shopping",
            "Bill Payment",
            "Charity",
            "Send Gift",
            "Split Bills",
            "Cash Back"
    };

    Integer img1[] = {
            R.drawable.ic_group_199,
            R.drawable.ic_group_109,
            R.drawable.ic_group_113
    };

    String txtsavingacct[] = {
            "Nearest Hospitals",
            "Nearest Garage",
            "Nearest Petrol Station"
    };

    String txtdiscount[] = {
            "Get Medical Assistance Nearest From you!",
            "Got up to 10% monthly interest!",
            "Got up to 10% monthly interest!"
    };

    public Fragment_Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViews();
    }

    public void setUpViews() {
        //rewards slider
        viewPager = (ViewPager) getView().findViewById(R.id.viewpager3);
        circleIndicator = (CircleIndicator) getView().findViewById(R.id.circleindicator3);
        view_pager_adapter_my_saving = new View_Pager_Adapter_my_saving(getFragmentManager());
        viewPager.setAdapter(view_pager_adapter_my_saving);
        circleIndicator.setViewPager(viewPager);
        view_pager_adapter_my_saving.registerDataSetObserver(circleIndicator.getDataSetObserver());

        //       promo recyclerview code is here:
        recyclerview1 = getView().findViewById(R.id.recycler2);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        recyclerview1.setLayoutManager(layoutManager1);
        recyclerview1.setItemAnimator(new DefaultItemAnimator());
        recyclerview1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        walletModel2ArrayList = new ArrayList<>();

        for (int i = 0; i < img1.length; i++) {
            WalletModel2 view1 = new WalletModel2(img1[i], txtsavingacct[i], txtdiscount[i]);
            walletModel2ArrayList.add(view1);
        }
        walletAdapter2 = new WalletAdapter2(getContext(), walletModel2ArrayList);
        recyclerview1.setAdapter(walletAdapter2);

        //initialise Text Views
        nameText = getView().findViewById(R.id.walletNameTextView);
        emailText = getView().findViewById(R.id.walletEmailTextView);

        //set values based on shared preferences
        User user = SharedPrefManager.getInstance(getContext()).getUser();
        nameText.setText(user.getName());
        emailText.setText(user.getEmail());

        ImageView hospitals = (ImageView) getView().findViewById(R.id.hospitals);
        hospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent;
                myIntent = new Intent(getActivity(), Specific_Insurance_Type.class);
                myIntent.putExtra("fragment", "medical");
                startActivity(myIntent);
            }
        });

        ImageView motorImage = getView().findViewById(R.id.motorInsurancePicture);
        motorImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Specific_Insurance_Type.class);
                intent.putExtra("fragment", "motor");
                startActivity(intent);
            }
        });

        ImageView domesticImage = getView().findViewById(R.id.domesticPicture);
        domesticImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Specific_Insurance_Type.class);
                intent.putExtra("fragment", "domestic");
                startActivity(intent);
            }
        });

        ImageView hamburgerMenuImage = getView().findViewById(R.id.hamburger_menu);
        hamburgerMenuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawerLayout = getActivity().findViewById(R.id.drawer_layout);
                drawerLayout.openDrawer(Gravity.START);
            }
        });

    }
}
