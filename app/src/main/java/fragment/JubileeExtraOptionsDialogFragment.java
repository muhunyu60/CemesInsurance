package fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import com.cemesinsurance.cemes_customer.R;

public class JubileeExtraOptionsDialogFragment extends AppCompatDialogFragment {
    private CheckBox maternity;
    private CheckBox dental;
    private CheckBox optical;
    private CheckBox personalAccident;
    private CheckBox lastExpense;
    private CheckBox outpatient;

    private HealthExtraOptionsOnClickListener healthExtraOptionsOnClickListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        healthExtraOptionsOnClickListener = (HealthExtraOptionsOnClickListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.jubilee_extra_options_dialog_fragment, null);

        maternity = view.findViewById(R.id.maternityCheckbox);
        dental = view.findViewById(R.id.dentalCheckbox);
        optical = view.findViewById(R.id.opticalCheckbox);
        personalAccident = view.findViewById(R.id.personalAccidentCheckbox);
        lastExpense = view.findViewById(R.id.lastExpenseCheckbox);
        outpatient = view.findViewById(R.id.outpatientCheckbox);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                healthExtraOptionsOnClickListener.applyExtras(
                        maternity.isChecked(),
                        dental.isChecked(),
                        optical.isChecked(),
                        personalAccident.isChecked(),
                        outpatient.isChecked()
                );
            }
        });

        return builder.create();
    }

    public interface HealthExtraOptionsOnClickListener {
        void applyExtras(Boolean maternity, Boolean dental, Boolean optical, Boolean personalAccident, Boolean outpatient);
    }
}
