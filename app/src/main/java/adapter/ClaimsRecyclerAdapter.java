package adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cemesinsurance.cemes_customer.R;

import java.util.ArrayList;

import model.ClaimModel;

public class ClaimsRecyclerAdapter extends RecyclerView.Adapter<ClaimsRecyclerAdapter.ClaimsViewHolder>{
    private ArrayList<ClaimModel> claimsList;

    public ClaimsRecyclerAdapter(ArrayList<ClaimModel> claimsList) {
        this.claimsList = claimsList;
    }

    public class ClaimsViewHolder extends RecyclerView.ViewHolder {
        public TextView claimTitle;
        public TextView claimDescription;
        public ImageView claimIcon;

        public ClaimsViewHolder(View itemView) {
            super(itemView);
            claimTitle = itemView.findViewById(R.id.claimTitle);
            claimDescription =  itemView.findViewById(R.id.claimDescription);
            claimIcon = itemView.findViewById(R.id.claimIcon);
        }
    }

    @NonNull
    @Override
    public ClaimsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.claim_item, parent, false);
        ClaimsViewHolder viewHolder = new ClaimsViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClaimsViewHolder holder, int position) {
        ClaimModel claim = claimsList.get(position);
        holder.claimTitle.setText(claim.getTitle());
        holder.claimDescription.setText(claim.getDescription());

        if(claim.getType() == "motor") {
            holder.claimIcon.setImageResource(R.drawable.ic_xmlid_1);
        } else {
            holder.claimIcon.setImageResource(R.drawable.ic_group_223);
        }

    }

    @Override
    public int getItemCount() {
        return claimsList.size();
    }


}
