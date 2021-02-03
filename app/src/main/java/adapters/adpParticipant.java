package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationfin.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import clases.ParticipantModel;

public class adpParticipant extends RecyclerView.Adapter<adpParticipant.ViewHolder> {

    private List<ParticipantModel> data;
    public adpParticipant(List<ParticipantModel> data){this.data = data;}

    public static boolean showShimmer = true;
    int cantShimmer = 8;

    public class ViewHolder extends RecyclerView.ViewHolder {
        ShimmerFrameLayout shimmerFrameLayout;
        TextView txtParticipant;
        ImageView btnShimmer;
        TextView txtCel;
        ImageView btnShimmer1;
        TextView txtEmail;
        ImageView btnShimmer2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shimmerFrameLayout = itemView.findViewById(R.id.shimmer);
            txtParticipant = (TextView) itemView.findViewById(R.id.txtAuthorName);
            btnShimmer = itemView.findViewById(R.id.imageViewliPart);
            txtCel = (TextView) itemView.findViewById(R.id.txtCel);
            btnShimmer1 = itemView.findViewById(R.id.imageViewliTel);
            txtEmail = (TextView) itemView.findViewById(R.id.txtEmail);
            btnShimmer2 = itemView.findViewById(R.id.imageViewliEm);
        }

        public void add_data(ParticipantModel valor) {
            txtParticipant.setText(valor.getName());
            txtCel.setText(valor.getPhone());
            txtEmail.setText(valor.getEmail());
        }
    }

    @NonNull
    @Override
    public adpParticipant.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_participant,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adpParticipant.ViewHolder holder, int position) {
        try {
            if(showShimmer)
            {
                holder.shimmerFrameLayout.startShimmer();
            }
            else
            {
                holder.shimmerFrameLayout.stopShimmer();
                holder.shimmerFrameLayout.setShimmer(null);
                holder.txtParticipant.setBackground(null);
                holder.txtCel.setBackground(null);
                holder.txtEmail.setBackground(null);
                holder.add_data(data.get(position));
            }
        }
        catch (Exception e){
        }
    }

    @Override
    public int getItemCount() {
        try{
            return showShimmer ? cantShimmer : data.size();
        }catch (Exception e) {return cantShimmer;}
    }
}
