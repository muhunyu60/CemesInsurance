package adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cemesinsurance.cemes_customer.R;

import java.text.NumberFormat;
import java.util.ArrayList;

import customfonts.MyTextView_SF_Pro_Display_Medium;
import model.AvailableMotorInsuranceModel;
import model.motorinsurancemodels.JubileeMotorModel;

public class AvailableMotorInsuranceAdapter extends RecyclerView.Adapter<AvailableMotorInsuranceAdapter.AvailableMotorInsuranceViewHolder> {
    public ArrayList<AvailableMotorInsuranceModel> availableMotorInsuranceModels;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    };

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
        private TextView price;
        private TextView insuranceTitle;
        private TextView windScreen;
        private TextView radio;
        private TextView excessProtector;
        private TextView PVT;
        private TextView lossOfValue;
        private TextView roadRescue;
        private ImageView logo;
        private MyTextView_SF_Pro_Display_Medium getQuote;

        public AvailableMotorInsuranceViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            price = itemView.findViewById(R.id.textViewPrice);
            insuranceTitle = itemView.findViewById(R.id.textViewInsuranceCompany);
            windScreen = itemView.findViewById(R.id.textViewWindScreenValue);
            radio = itemView.findViewById(R.id.textViewRadioValue);
            excessProtector = itemView.findViewById(R.id.textViewExcessProtectorValue);
            PVT = itemView.findViewById(R.id.textViewPVTValue);
            lossOfValue = itemView.findViewById(R.id.textViewLossOfUseValue);
            roadRescue = itemView.findViewById(R.id.textViewRoadRescueValue);
            logo = itemView.findViewById(R.id.insuranceLogo);
            getQuote = itemView.findViewById(R.id.get_quote);

            getQuote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public AvailableMotorInsuranceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.available_motor_insurance_item, parent, false);
        AvailableMotorInsuranceViewHolder viewHolder = new AvailableMotorInsuranceViewHolder(v, onItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AvailableMotorInsuranceViewHolder holder, int position) {
        AvailableMotorInsuranceModel model = availableMotorInsuranceModels.get(position);

        holder.logo.setImageResource(model.getLogoId());
        holder.insuranceTitle.setText(model.getInsuranceName());
        NumberFormat nf = NumberFormat.getInstance();
        if(model.includesLossOfUse()) {
            holder.lossOfValue.setText(nf.format((double) JubileeMotorModel.EXCESS_PROTECTOR_PRICE));
        } else {
            holder.lossOfValue.setText("0");
        }

        if(model.includesExcessProtector()) {
            holder.excessProtector.setText(nf.format((double) JubileeMotorModel.EXCESS_PROTECTOR_PRICE));
        } else {
            holder.excessProtector.setText("0");
        }

        if(model.includesPVT()) {
            holder.PVT.setText((JubileeMotorModel.PVT_PRICE));
        } else {
            holder.PVT.setText("0");
        }

        if(model.includesRoadRescue()) {
            holder.roadRescue.setText(nf.format((double) JubileeMotorModel.ROAD_RESCUE_PRICE));
        } else {
            holder.roadRescue.setText("0");
        }

        holder.windScreen.setText(nf.format(model.getWindScreenValue()));
        holder.radio.setText(nf.format(model.getRadioValue()));

        int premium = model.getPrice();
        holder.price.setText(nf.format((double) premium));
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
