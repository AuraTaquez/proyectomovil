package adaptadores;

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

import clases.Participante;

public class adpParticipante extends RecyclerView.Adapter<adpParticipante.ViewHolder> {

    private List<Participante> datos;
    public adpParticipante(List<Participante> datos){this.datos = datos;}

    public static boolean showShimmer = true;
    int cantShimmer = 8;

    public class ViewHolder extends RecyclerView.ViewHolder {
        ShimmerFrameLayout shimmerFrameLayout;
        TextView txtParticipante;
        ImageView btnShimmer;
        TextView txtCel;
        ImageView btnShimmer1;
        TextView txtEmail;
        ImageView btnShimmer2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shimmerFrameLayout = itemView.findViewById(R.id.shimmer);
            txtParticipante = (TextView) itemView.findViewById(R.id.txtNombreAutor);
            btnShimmer = itemView.findViewById(R.id.imageViewliPart);
            txtCel = (TextView) itemView.findViewById(R.id.txtCelular);
            btnShimmer1 = itemView.findViewById(R.id.imageViewliTel);
            txtEmail = (TextView) itemView.findViewById(R.id.txtEmail);
            btnShimmer2 = itemView.findViewById(R.id.imageViewliEm);
        }

        public void add_datos(Participante valor) {
            txtParticipante.setText(valor.getNombre());
            txtCel.setText(valor.getTelefono());
            txtEmail.setText(valor.getEmail());
        }
    }

    @NonNull
    @Override
    public adpParticipante.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_participante,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adpParticipante.ViewHolder holder, int position) {
        try {
            if(showShimmer)
            {
                holder.shimmerFrameLayout.startShimmer();
            }
            else
            {
                holder.shimmerFrameLayout.stopShimmer();
                holder.shimmerFrameLayout.setShimmer(null);
                holder.txtParticipante.setBackground(null);
                holder.txtCel.setBackground(null);
                holder.txtEmail.setBackground(null);
                holder.add_datos(datos.get(position));
            }
        }
        catch (Exception e){
        }
    }

    @Override
    public int getItemCount() {
        try{
            return showShimmer ? cantShimmer : datos.size();
        }catch (Exception e) {return cantShimmer;}
    }
}
