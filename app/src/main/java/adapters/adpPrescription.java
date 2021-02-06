package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplicationfin.R;

import java.util.ArrayList;
import java.util.List;

import clases.PrescriptionModel;


public class adpPrescription extends RecyclerView.Adapter<adpPrescription.ViewHolderPRES>{


    private List<PrescriptionModel> prescriptionList;
    private Context Ctx;
    private String torre="https://firebasestorage.googleapis.com/v0/b/rehabilitacioniot-a9f5a.appspot.com/o/dispotivos%2Fistockphoto-1083263642-612x612.jpg?alt=media&token=c0bcd3ad-418d-4647-ab92-3a6234fe80b0";
    private String pelota="https://firebasestorage.googleapis.com/v0/b/rehabilitacioniot-a9f5a.appspot.com/o/dispotivos%2Fdise%C3%B1o-del-logo-de-la-pelota-f%C3%BAtbol-icono-vector-ilustraci%C3%B3n-vectorial-184321567.jpg?alt=media&token=299a2ef7-91bf-41f9-b02e-4c8bbe74a08f";
    private String botella="";

    public adpPrescription(List<PrescriptionModel> prescriptionList, Context mCtx) {
        this.prescriptionList = prescriptionList;
        Ctx=mCtx;
    }

    @NonNull
    @Override
    public ViewHolderPRES onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.item_therapies, null);
        return new ViewHolderPRES(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPRES holder, int position) {
        PrescriptionModel prescriptionModel = prescriptionList.get(position);
        holder.txtTherapyType.setText(prescriptionModel.getTypetheraphy());
        holder.txtDispositive.setText(prescriptionModel.getDevice());
        holder.txtDate.setText(prescriptionModel.getDate());
        holder.txtTerapistaEmail.setText(prescriptionModel.getTherapistemail());
        String patch="";
        if (prescriptionModel.getDevice().equals("0"))
            patch=torre;
        if (prescriptionModel.getDevice().equals("1"))
            patch=pelota;
        if (prescriptionModel.getDevice().equals("2"))
            patch=botella;

        Glide.with(Ctx)
                .load(patch)
                .into(holder.imgDispositive);
    }

    @Override
    public int getItemCount() {
        return prescriptionList.size();
    }


    public class ViewHolderPRES extends RecyclerView.ViewHolder{
        private TextView txtTherapyType;
        private TextView txtDispositive;
        private TextView txtDate;
        private TextView txtTerapistaEmail;
        private ImageView imgDispositive;
        public View view;

        public ViewHolderPRES(View view){
            super(view);
            this.txtTherapyType = (TextView) view.findViewById(R.id.txtTherapyType);
            this.txtDispositive = (TextView) view.findViewById(R.id.txtDispositive);
            this.txtDate = (TextView) view.findViewById(R.id.txtDate);
            this.txtTerapistaEmail = (TextView) view.findViewById(R.id.txtTerapistaEmail);
            this.imgDispositive = (ImageView) view.findViewById(R.id.imgDispositive);

        }
    }
}
