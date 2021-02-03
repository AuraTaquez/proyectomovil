package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationfin.R;
import java.util.ArrayList;

import clases.DispositiveModel;

public class adpDispositive extends RecyclerView.Adapter<adpDispositive.ViewHolderDIS>{

    private int resource;
    private ArrayList<DispositiveModel> dispositiveList;

    public adpDispositive(ArrayList<DispositiveModel> dispositiveList, int resource) {
        this.resource = resource;
        this.dispositiveList = dispositiveList;
    }

    @NonNull
    @Override
    public ViewHolderDIS onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new adpDispositive.ViewHolderDIS(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDIS holder, int position) {
        DispositiveModel dispositivosModel = dispositiveList.get(position);
        holder.txtMAC.setText(dispositivosModel.getMac());
        holder.txtDispositive.setText(dispositivosModel.getDispositive());
        holder.txtState.setText(dispositivosModel.getState());
        holder.txtObservation.setText(dispositivosModel.getObservations());
    }

    @Override
    public int getItemCount() {
        return dispositiveList.size();
    }

    public class ViewHolderDIS extends RecyclerView.ViewHolder{
        private TextView txtMAC;
        private TextView txtDispositive;
        private TextView txtState;
        private TextView txtObservation;
        public View view;

        public ViewHolderDIS(View view){
            super(view);
            this.txtMAC = (TextView) view.findViewById(R.id.txtviewMAC);
            this.txtDispositive = (TextView) view.findViewById(R.id.txtviewDispositive);
            this.txtState = (TextView) view.findViewById(R.id.txtviewState);
            this.txtObservation = (TextView) view.findViewById(R.id.txtviewObservations);
        }
    }

}
