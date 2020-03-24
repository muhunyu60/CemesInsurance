package adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cemesinsurance.cemes_customer.R;

import java.util.ArrayList;

import model.AvailableMotorInsuranceModel;
import model.motorinsurancemodels.JubileeMotorModel;

public class AvailableMotorInsuranceAdapter extends RecyclerView.Adapter<AvailableMotorInsuranceAdapter.AvailableMotorInsuranceViewHolder> {
    ArrayList<AvailableMotorInsuranceModel> availableMotorInsuranceModels;

    public AvailableMotorInsuranceAdapter() {
        availableMotorInsuranceModels = new ArrayList<AvailableMotorInsuranceModel>();
        try {
            availableMotorInsuranceModels.add(
                    new JubileeMotorModel(
                            0,
                            1990,
                            "private",
                            "24/12/2020"
                    )
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public class AvailableMotorInsuranceViewHolder extends RecyclerView.ViewHolder{
        public TextView price;
        public TextView insuranceTitle;
        public TextView windScreen;
        public TextView radio;
        public TextView excessProtector;
        public TextView PVT;
        public TextView lossOfValue;

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
    public AvailableMotorInsuranceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.available_motor_insurance_item, parent, false);
        AvailableMotorInsuranceViewHolder viewHolder = new AvailableMotorInsuranceViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AvailableMotorInsuranceViewHolder holder, int position) {
        holder.insuranceTitle.setText("Insurance Company");
        holder.lossOfValue.setText("0");
        holder.excessProtector.setText("0");
        holder.windScreen.setText("0");
        holder.price.setText("0");
        holder.radio.setText("0");
        holder.PVT.setText("0");
    }

    @Override
    public int getItemCount() {
        return availableMotorInsuranceModels.size();
    }

}
