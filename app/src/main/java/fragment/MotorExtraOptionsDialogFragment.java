package fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
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
    MotorExtraOptionsDialogListener listener;
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
                        String windscreenValue = windScreenEditText.getText().toString();
                        if (windscreenValue.trim().length() == 0)  {
                            windscreenValue = "0";
                        }
                        String radioValue = radioEditText.getText().toString();
                        if (radioValue.trim().length() == 0)  {
                            radioValue = "0";
                        }
                        Boolean excessProtector = excessProtectorCheckBox.isChecked();
                        Boolean politicalViolenceAndTerrorism = politicalViolenceAndTerrorismCheckBox.isChecked();
                        Boolean lossOfUse = lossOfUseCheckBox.isChecked();
                        Boolean roadRescue = roadRescueCheckBox.isChecked();

                        listener.passOptions(
                                windscreenValue,
                                radioValue,
                                excessProtector,
                                politicalViolenceAndTerrorism,
                                lossOfUse,
                                roadRescue
                        );
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (MotorExtraOptionsDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement passOptions");
        }
    }

    public interface MotorExtraOptionsDialogListener {
        void passOptions(String windscreenValue,
                         String radioValue,
                         Boolean excessProtector,
                         Boolean politicalViolenceAndTerrorism,
                         Boolean lossOfUse,
                         Boolean roadRescue);
    }
}
