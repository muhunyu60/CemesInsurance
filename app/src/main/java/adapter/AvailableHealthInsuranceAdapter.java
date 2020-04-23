package adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cemesinsurance.cemes_customer.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import model.AvailableHealthInsuranceInterface;
import model.healthinsurancemodels.JubileeHealthInsurance;

public class AvailableHealthInsuranceAdapter extends RecyclerView.Adapter<AvailableHealthInsuranceAdapter.AvailableHealthInsuranceHolder> {
    List<AvailableHealthInsuranceInterface> healthProviders = new ArrayList<>();

    public AvailableHealthInsuranceAdapter(String coverLimit, String applicantDOB, Boolean hasPreExistingCondition) {
            healthProviders.add(new JubileeHealthInsurance(
                    coverLimit,
                    applicantDOB,
                    hasPreExistingCondition
            ));
    }

    public AvailableHealthInsuranceAdapter(String coverLimit, String applicantDOB, String spouseDOB, Boolean hasPreExistingCondition) {
        healthProviders.add(new JubileeHealthInsurance(
                coverLimit,
                applicantDOB,
                spouseDOB,
                hasPreExistingCondition
        ));
    }

    public AvailableHealthInsuranceAdapter(String coverLimit, String applicantDOB, String spouseDOB, int numberOfChildren, Boolean hasPreExistingCondition) {
        healthProviders.add(new JubileeHealthInsurance(
                coverLimit,
                applicantDOB,
                spouseDOB,
                numberOfChildren,
                hasPreExistingCondition
        ));
    }

    public AvailableHealthInsuranceAdapter(String coverLimit, String applicantDOB, int numberOfChildren, Boolean hasPreExistingCondition) {
        healthProviders.add(new JubileeHealthInsurance(
                coverLimit,
                applicantDOB,
                numberOfChildren,
                hasPreExistingCondition
        ));
    }

    @NonNull
    @Override
    public AvailableHealthInsuranceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.available_health_insurance_item, parent, false);
        return new AvailableHealthInsuranceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AvailableHealthInsuranceHolder holder, int position) {

        String price = NumberFormat.getInstance().format(healthProviders.get(position).getPrice());
        String name = healthProviders.get(position).getInsuranceProviderName();

        holder.insurancePriceTextView.setText(price);
        holder.insuranceNameTextView.setText(name);
    }

    @Override
    public int getItemCount() {
        return healthProviders.size();
    }

    public class AvailableHealthInsuranceHolder extends RecyclerView.ViewHolder {

        private TextView insuranceNameTextView;
        private TextView insurancePriceTextView;

        public AvailableHealthInsuranceHolder(View itemView) {
            super(itemView);
            insuranceNameTextView = itemView.findViewById(R.id.insuranceNameTextView);
            insurancePriceTextView = itemView.findViewById(R.id.insurancePrice);
        }
    }
}
