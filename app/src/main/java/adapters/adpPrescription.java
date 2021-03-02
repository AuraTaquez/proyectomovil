package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplicationfin.R;
import java.util.List;

import clases.PrescriptionModel;


public class adpPrescription extends RecyclerView.Adapter<adpPrescription.ViewHolderPRES>{

    private List<PrescriptionModel> prescriptionList;
    private Context Ctx;
    private String dispositivo="";


    public adpPrescription(List<PrescriptionModel> prescriptionList, Context mCtx) {
        this.prescriptionList = prescriptionList;
        Ctx=mCtx;
    }

    @NonNull
    @Override
    public ViewHolderPRES onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.item_prescription, null);
        return new ViewHolderPRES(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPRES holder, int position) {
        PrescriptionModel prescriptionModel = prescriptionList.get(position);
        holder.txtviewTypeTherapy.setText(prescriptionModel.getTypetheraphy());
        holder.txtviewDate.setText(prescriptionModel.getDate());
        holder.txtviewStatus.setText(prescriptionModel.getStatus());
        holder.txtviewCommentary.setText(prescriptionModel.getCommentary());

        if (prescriptionModel.getDevice().equals("0"))
            dispositivo="Torre";
        if (prescriptionModel.getDevice().equals("1"))
            dispositivo="Pelota";
        if (prescriptionModel.getDevice().equals("2"))
            dispositivo="Botella";

        holder.txtviewDevice.setText(dispositivo);

    }

    @Override
    public int getItemCount() {
        return prescriptionList.size();
    }


    public class ViewHolderPRES extends RecyclerView.ViewHolder{
        private TextView txtviewTypeTherapy;
        private TextView txtviewDevice;
        private TextView txtviewDate;
        private TextView txtviewStatus;
        private TextView txtviewCommentary;
        public View view;

        public ViewHolderPRES(View view){
            super(view);
            this.txtviewTypeTherapy = (TextView) view.findViewById(R.id.txtviewTypeTherapy);
            this.txtviewDevice = (TextView) view.findViewById(R.id.txtviewDevice);
            this.txtviewDate = (TextView) view.findViewById(R.id.txtviewDate);
            this.txtviewStatus = (TextView) view.findViewById(R.id.txtviewStatus);
            this.txtviewCommentary = (TextView) view.findViewById(R.id.txtviewCommentary);

        }
    }
}
