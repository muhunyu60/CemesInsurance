package adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AvailableHealthInsuranceAdapter extends RecyclerView.Adapter<AvailableHealthInsuranceAdapter.AvailableHealthInsuranceHolder> {

    @NonNull
    @Override
    public AvailableHealthInsuranceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AvailableHealthInsuranceHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class AvailableHealthInsuranceHolder extends RecyclerView.ViewHolder {

        private TextView insuranceNameTextView;
        private TextView insurancePriceTextView;

        public AvailableHealthInsuranceHolder(View itemView) {
            super(itemView);
        }
    }
}
