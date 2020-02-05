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

import model.WalletModel2;

/**
 * Created by wolfsoft4 on 1/10/18.
 */

public class WalletAdapter2 extends RecyclerView.Adapter<WalletAdapter2.ViewHolder> {
    Context context;
    private ArrayList<WalletModel2> walletModel2ArrayList;


    public WalletAdapter2(Context context, ArrayList<WalletModel2> walletModel2ArrayList) {
        this.context = context;
        this.walletModel2ArrayList = walletModel2ArrayList;
    }

    @Override
    public WalletAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_wallet2,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WalletAdapter2.ViewHolder holder, int position) {

            holder.img1.setImageResource(walletModel2ArrayList.get(position).getImg1());
            holder.txtsavingacct.setText(walletModel2ArrayList.get(position).getTxtsavingacct());
            holder.txtdiscount.setText(walletModel2ArrayList.get(position).getTxtdiscount());

    }

    @Override
    public int getItemCount() {
        return walletModel2ArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img1;
        TextView txtsavingacct,txtdiscount;


        public ViewHolder(View itemView) {
            super(itemView);

            img1=itemView.findViewById(R.id.img1);
            txtsavingacct=itemView.findViewById(R.id.txtsavingacct);
            txtdiscount=itemView.findViewById(R.id.txtdiscount);


        }
    }
}
