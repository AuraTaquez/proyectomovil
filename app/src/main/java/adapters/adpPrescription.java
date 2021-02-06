package adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationfin.R;

import java.util.ArrayList;

import clases.PrescriptionModel;


public class adpPrescription extends RecyclerView.Adapter<adpPrescription.ViewHolderPRES>{

    private int resource;
    private ArrayList<PrescriptionModel> prescriptionList;

    public adpPrescription(ArrayList<PrescriptionModel> prescriptionList, int resource) {
        this.resource = resource;
        this.prescriptionList = prescriptionList;
    }

    @NonNull
    @Override
    public ViewHolderPRES onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new adpPrescription.ViewHolderPRES(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPRES holder, int position) {
        PrescriptionModel prescriptionModel = prescriptionList.get(position);
        holder.pacient.setText(prescriptionModel.getPacient());
        holder.typetherapy.setText(prescriptionModel.getTypeTherapy());
        holder.device.setText(prescriptionModel.getDevice());
        holder.date.setText(prescriptionModel.getDate());
        holder.status.setText(prescriptionModel.getStatus());
        holder.commentary.setText(prescriptionModel.getCommentary());
    }

    @Override
    public int getItemCount() {
        return prescriptionList.size();
    }


    public class ViewHolderPRES extends RecyclerView.ViewHolder{
        private TextView pacient;
        private TextView typetherapy;
        private TextView device;
        private TextView date;
        private TextView status;
        private TextView commentary;
        public View view;

        public ViewHolderPRES(View view){
            super(view);
            this.pacient = (TextView) view.findViewById(R.id.txtviewPacient);
            this.typetherapy = (TextView) view.findViewById(R.id.txtviewTypeTherapy);
            this.device = (TextView) view.findViewById(R.id.txtviewDevice);
            this.date = (TextView) view.findViewById(R.id.txtviewDate);
            this.status = (TextView) view.findViewById(R.id.txtviewStatus);
            this.commentary = (TextView) view.findViewById(R.id.txtviewCommentary);
        }
    }
}
