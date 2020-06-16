package fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.cemesinsurance.cemes_customer.Add_Claim;
import com.cemesinsurance.cemes_customer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Volley.URLs;
import Volley.VolleySingleton;
import adapter.ClaimsRecyclerAdapter;
import model.ClaimModel;
import model.User;
import state.SharedPrefManager;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Claims extends Fragment {
    private ClaimsRecyclerAdapter claimsAdapter;
    private RecyclerView claimsRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton floatingActionButton;

    ArrayList<ClaimModel> myClaims = new ArrayList<>();

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

        claimsAdapter = new ClaimsRecyclerAdapter(myClaims);
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

        getMyClaims();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getMyClaims();
    }

    public static Bitmap textAsBitmap(String text, float textSize, int textColor) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent();
        int width = (int)  (paint.measureText(text) + 0.0f);
        int height = (int) (baseline + paint.descent() + 0.0f);
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(image);
        canvas.drawText(text, 0, baseline, paint);
        return image;
    }

    void getMyClaims() {
        final User user = SharedPrefManager.getInstance(getContext()).getUser();
        myClaims = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URLs.GET_CLAIM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            if(object.getString("message").equalsIgnoreCase("successful")) {
                                JSONArray array = object.getJSONArray("claims");

                                for(int i = 0; i < array.length(); i++) {
                                    JSONObject jsonObject = array.getJSONObject(i);
                                    String claimType = jsonObject.getString("claim_type");
                                    String description = jsonObject.getString("description");
                                    myClaims.add(new ClaimModel(claimType, claimType, description));

                                    claimsAdapter = new ClaimsRecyclerAdapter(myClaims);
                                    layoutManager = new LinearLayoutManager(getActivity());
                                    claimsRecyclerView.setLayoutManager(layoutManager);
                                    claimsRecyclerView.setAdapter(claimsAdapter);
                                }
                            }

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
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", user.getEmail());
                return params;
            }
        };

        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }
}
