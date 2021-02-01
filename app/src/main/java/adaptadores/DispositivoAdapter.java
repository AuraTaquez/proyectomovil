package adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationfin.R;


import java.util.ArrayList;

import clases.DispositivosModel;

public class DispositivoAdapter extends RecyclerView.Adapter<adaptadores.DispositivoAdapter.ViewHolderDIS>{
    private int resource;
    private ArrayList<DispositivosModel> dispositivoList;

    public DispositivoAdapter(ArrayList<DispositivosModel> dispositivoList, int resource) {
        this.resource = resource;
        this.dispositivoList = dispositivoList;
    }

    @NonNull
    @Override
    public ViewHolderDIS onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new adaptadores.DispositivoAdapter.ViewHolderDIS(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDIS holder, int position) {
        DispositivosModel dispositivosModel = dispositivoList.get(position);
        holder.txtMAC.setText(dispositivosModel.getMac());
        holder.txtDispositivo.setText(dispositivosModel.getDispositivo());
        holder.txtEstado.setText(dispositivosModel.getEstado());
        holder.txtObservacion.setText(dispositivosModel.getObservaciones());
    }

    @Override
    public int getItemCount() {
        return dispositivoList.size();
    }


    public class ViewHolderDIS extends RecyclerView.ViewHolder{
        private TextView txtMAC;
        private TextView txtDispositivo;
        private TextView txtEstado;
        private TextView txtObservacion;
        public View view;

        public ViewHolderDIS(View view){
            super(view);
            this.txtMAC = (TextView) view.findViewById(R.id.txtviewMAC);
            this.txtDispositivo = (TextView) view.findViewById(R.id.txtviewDispositivo);
            this.txtEstado = (TextView) view.findViewById(R.id.txtviewEstado);
            this.txtObservacion = (TextView) view.findViewById(R.id.txtviewObservaciones);
        }
    }

}
