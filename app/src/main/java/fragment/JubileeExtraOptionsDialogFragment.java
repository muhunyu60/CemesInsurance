package fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import com.cemesinsurance.cemes_customer.R;

public class JubileeExtraOptionsDialogFragment extends AppCompatDialogFragment {
    CheckBox maternity;
    CheckBox dental;
    CheckBox optical;
    CheckBox personalAccident;
    CheckBox lastExpense;
    CheckBox outpatient;

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

        return builder.create();
    }
}
