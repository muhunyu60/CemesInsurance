package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cemesinsurance.cemes_customer.R;

import java.util.ArrayList;

import model.WalletModel;

/**
 * Created by wolfsoft4 on 28/9/18.
 */

public class WalletAdapter1 extends RecyclerView.Adapter<WalletAdapter1.ViewHolder> {
    Context context;
    private ArrayList<WalletModel> walletModelArrayList;

    public WalletAdapter1(Context context, ArrayList<WalletModel> walletModelArrayList) {
        this.context = context;
        this.walletModelArrayList = walletModelArrayList;
    }

    @Override
    public WalletAdapter1.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_wallet,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WalletAdapter1.ViewHolder holder, int position) {
        holder.oval.setImageResource(walletModelArrayList.get(position).getOval());
        holder.txtshopping.setText(walletModelArrayList.get(position).getTxtshopping());


    }

    @Override
    public int getItemCount() {
        return walletModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView oval;
        TextView txtshopping;

        public ViewHolder(View itemView) {
            super(itemView);

            oval=itemView.findViewById(R.id.oval);
            txtshopping=itemView.findViewById(R.id.txtshopping);

        }
    }
}
