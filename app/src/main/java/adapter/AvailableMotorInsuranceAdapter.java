package adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cemesinsurance.cemes_customer.R;

public class AvailableMotorInsuranceAdapter extends RecyclerView.Adapter {

    public class AvailableMotorInsuranceViewHolder extends RecyclerView.ViewHolder{
        TextView price;
        TextView insuranceTitle;
        TextView windScreen;
        TextView radio;
        TextView excessProtector;
        TextView PVT;
        TextView lossOfValue;

        public AvailableMotorInsuranceViewHolder(View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.textViewPrice);
            insuranceTitle = itemView.findViewById(R.id.textViewInsuranceCompany);
            windScreen = itemView.findViewById(R.id.textViewWindScreenValue);
            radio = itemView.findViewById(R.id.textViewRadioValue);
            excessProtector = itemView.findViewById(R.id.textViewExcessProtectorValue);
            PVT = itemView.findViewById(R.id.textViewPVTValue);
            lossOfValue = itemView.findViewById(R.id.textViewLossOfUseValue);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.available_motor_insurance_item, parent, false);
        AvailableMotorInsuranceViewHolder viewHolder = new AvailableMotorInsuranceViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
