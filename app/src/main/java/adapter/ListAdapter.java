package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cemesinsurance.cemes_customer.Account_05;
import com.cemesinsurance.cemes_customer.Charity;
import com.cemesinsurance.cemes_customer.Fingerprint_04;
import com.cemesinsurance.cemes_customer.Gift;
import com.cemesinsurance.cemes_customer.Login_01;
import com.cemesinsurance.cemes_customer.My_Saving_11;
import com.cemesinsurance.cemes_customer.My_Wallet_07;
import com.cemesinsurance.cemes_customer.R;
import com.cemesinsurance.cemes_customer.Registration_02;
import com.cemesinsurance.cemes_customer.Transaction_08;
import com.cemesinsurance.cemes_customer.Verification;
import com.cemesinsurance.cemes_customer.Web1920_12;
import com.cemesinsurance.cemes_customer.Web1920_13;
import com.cemesinsurance.cemes_customer.Web1920_14;
import com.cemesinsurance.cemes_customer.Web1920_15;
import com.cemesinsurance.cemes_customer.Web1920_16;

import java.util.ArrayList;

import model.ListModel;

/**
 * Created by wolfsoft4 on 24/9/18.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    Context context;
    private ArrayList<ListModel> listModelArrayList;

    public ListAdapter(Context context, ArrayList<ListModel> listModelArrayList) {
        this.context = context;
        this.listModelArrayList = listModelArrayList;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, final int position) {

        holder.title.setText(listModelArrayList.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(position==0){
                    Intent i = new Intent(context, Login_01.class);
                    context.startActivity(i);}
                if(position==1){
                    Intent i = new Intent(context, Registration_02.class);
                    context.startActivity(i);}
                if(position==2){
                    Intent i = new Intent(context, Verification.class);
                    context.startActivity(i);}
                if(position==3){
                    Intent i = new Intent(context, Fingerprint_04.class);
                    context.startActivity(i);}
                if(position==4){
                    Intent i = new Intent(context, Account_05.class);
                    context.startActivity(i);}
                if(position==5){
                    Intent i = new Intent(context, My_Wallet_07.class);
                    context.startActivity(i);}
                if(position==6){
                    Intent i = new Intent(context, Charity.class);
                    context.startActivity(i);}
                if(position==7){
                    Intent i = new Intent(context, Gift.class);
                    context.startActivity(i);}
                if(position==8){
                    Intent i = new Intent(context, My_Saving_11.class);
                    context.startActivity(i);}
                if(position==9){
                    Intent i = new Intent(context, Web1920_12.class);
                    context.startActivity(i);}
                if(position==10){
                    Intent i = new Intent(context, Web1920_13.class);
                    context.startActivity(i);}
                if(position==11){
                    Intent i = new Intent(context, Web1920_14.class);
                    context.startActivity(i);}
                if(position==12){
                    Intent i = new Intent(context, Web1920_15.class);
                    context.startActivity(i);}
                if(position==13){
                    Intent i = new Intent(context,Web1920_16.class);
                    context.startActivity(i);}
                if(position==14){
                    Intent i = new Intent(context,Transaction_08.class);
                    context.startActivity(i);}



            }
        });

    }

    @Override
    public int getItemCount() {
        return listModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;


        public ViewHolder(View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.title);
        }
    }
}
