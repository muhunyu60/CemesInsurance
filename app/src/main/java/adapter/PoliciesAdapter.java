package adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cemesinsurance.cemes_customer.R;

import java.util.ArrayList;

import model.PolicyInterface;

public class PoliciesAdapter extends RecyclerView.Adapter<PoliciesAdapter.PoliciesViewHolder> {
    private ArrayList<PolicyInterface> policyList;

    public PoliciesAdapter(ArrayList<PolicyInterface> policyList) {
        this.policyList = policyList;
    }

    @NonNull
    @Override
    public PoliciesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.policy_item, parent, false);
        return new PoliciesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PoliciesViewHolder holder, int position) {
        PolicyInterface policy = policyList.get(position);
        holder.insuranceNameTextView.setText(policy.getUnderwriterName());
        holder.insuranceCostTextView.setText(String.valueOf(policy.getInsuranceCost()));
        
    }

    @Override
    public int getItemCount() {
        return policyList.size();
    }

    public class PoliciesViewHolder extends RecyclerView.ViewHolder{
        public TextView insuranceNameTextView;
        public TextView insuranceCostTextView;
        public ImageView insuranceLogoImageView;

        public PoliciesViewHolder(View itemView) {
            super(itemView);
            insuranceCostTextView = itemView.findViewById(R.id.insuranceCostTextView);
            insuranceNameTextView = itemView.findViewById(R.id.underwriterTextView);
            insuranceLogoImageView = itemView.findViewById(R.id.underwriterLogo);
        }
    }
}
