package fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cemesinsurance.cemes_customer.R;


/**
 * Created by wolfsoft4 on 21/8/18.
 */

public class Overview_16 extends Fragment implements View.OnClickListener {

    TextView txt1,txt2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.overview_16,container,false);

        txt1 = (view).findViewById(R.id.txt1);
        txt2 = (view).findViewById(R.id.txt2);


        txt1.setOnClickListener(this);
        txt2.setOnClickListener(this);


        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){


            case R.id.txt1:

                txt1.setBackgroundResource(R.drawable.rect_yellow);
                txt2.setBackgroundResource(R.drawable.rect_trans_bg);

                break;

            case R.id.txt2:

                txt1.setBackgroundResource(R.drawable.rect_trans_bg);
                txt2.setBackgroundResource(R.drawable.rect_yellow);

                break;
        }

    }
}
