package fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.cemesinsurance.cemes_customer.R;

public class MotorExtraOptionsDialogFragment extends AppCompatDialogFragment {
    EditText windScreenEditText;
    EditText radioEditText;
    CheckBox excessProtectorCheckBox;
    CheckBox politicalViolenceAndTerrorismCheckBox;
    CheckBox lossOfUseCheckBox;
    CheckBox roadRescueCheckBox;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.extra_options_motor, null);

        windScreenEditText = view.findViewById(R.id.windScreenEditText);
        radioEditText = view.findViewById(R.id.radioEditText);
        excessProtectorCheckBox = view.findViewById(R.id.excessProtectorChkBox);
        politicalViolenceAndTerrorismCheckBox = view.findViewById(R.id.politicalViolenceChkBox);
        lossOfUseCheckBox = view.findViewById(R.id.lossOfUseChkBox);
        roadRescueCheckBox = view.findViewById(R.id.roadRescueChkBox);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        return builder.create();
    }
}
