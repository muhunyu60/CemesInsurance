package adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cemesinsurance.cemes_customer.R;

import java.text.NumberFormat;
import java.util.ArrayList;

import model.AvailableMotorInsuranceModel;
import model.motorinsurancemodels.JubileeMotorModel;

public class AvailableMotorInsuranceAdapter extends RecyclerView.Adapter<AvailableMotorInsuranceAdapter.AvailableMotorInsuranceViewHolder> {
    ArrayList<AvailableMotorInsuranceModel> availableMotorInsuranceModels;

    public AvailableMotorInsuranceAdapter(double carPrice, int carManufactureYear, String carClass, String insuranceStartDate, String carUse) {
        availableMotorInsuranceModels = new ArrayList<>();
        availableMotorInsuranceModels.add(
                new JubileeMotorModel(
                        carPrice,
                        carManufactureYear,
                        carClass,
                        insuranceStartDate,
                        carUse
                )
        );
    }

    public class AvailableMotorInsuranceViewHolder extends RecyclerView.ViewHolder{
        public TextView price;
        public TextView insuranceTitle;
        public TextView windScreen;
        public TextView radio;
        public TextView excessProtector;
        public TextView PVT;
        public TextView lossOfValue;
        public TextView roadRescue;

        public AvailableMotorInsuranceViewHolder(View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.textViewPrice);
            insuranceTitle = itemView.findViewById(R.id.textViewInsuranceCompany);
            windScreen = itemView.findViewById(R.id.textViewWindScreenValue);
            radio = itemView.findViewById(R.id.textViewRadioValue);
            excessProtector = itemView.findViewById(R.id.textViewExcessProtectorValue);
            PVT = itemView.findViewById(R.id.textViewPVTValue);
            lossOfValue = itemView.findViewById(R.id.textViewLossOfUseValue);
            roadRescue = itemView.findViewById(R.id.textViewRoadRescueValue);
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
        AvailableMotorInsuranceModel model = availableMotorInsuranceModels.get(position);

        holder.insuranceTitle.setText(model.getInsuranceName());
        if(model.includesLossOfUse()) {
            holder.lossOfValue.setText(String.valueOf(JubileeMotorModel.EXCESS_PROTECTOR_PRICE));
        } else {
            holder.lossOfValue.setText("0");
        }

        if(model.includesExcessProtector()) {
            holder.excessProtector.setText(String.valueOf(JubileeMotorModel.EXCESS_PROTECTOR_PRICE));
        } else {
            holder.excessProtector.setText("0");
        }

        if(model.includesPVT()) {
            holder.PVT.setText(String.valueOf(JubileeMotorModel.PVT_PRICE));
        } else {
            holder.PVT.setText("0");
        }

        if(model.includesRoadRescue()) {
            holder.roadRescue.setText(String.valueOf(JubileeMotorModel.ROAD_RESCUE_PRICE));
        } else {
            holder.roadRescue.setText("0");
        }

        holder.windScreen.setText(String.valueOf(model.getWindScreenValue()));
        holder.radio.setText(String.valueOf(model.getRadioValue()));

        int premium = model.getPrice();
        NumberFormat nf = NumberFormat.getInstance();
        holder.price.setText(nf.format(premium));
    }

    @Override
    public int getItemCount() {
        return availableMotorInsuranceModels.size();
    }

    public void setExtras(Double windScreenValue, Double radioValue, Boolean includesExcessProtector, Boolean includesPVT, Boolean includesLossOfUse, Boolean includesRoadRescue) {
        for (AvailableMotorInsuranceModel model : availableMotorInsuranceModels) {
            model.setExtras(
                    windScreenValue,
                    radioValue,
                    includesExcessProtector,
                    includesPVT,
                    includesLossOfUse,
                    includesRoadRescue
            );
        }
        notifyDataSetChanged();
    }

}
