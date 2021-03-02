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

import java.util.List;

import clases.Ejercicios;


public class adpExercise extends RecyclerView.Adapter<adpExercise.ViewHolderE>{

    private List<Ejercicios> exerciseList;
    private Context Ctx;
    private String torre="84:F3:EB:E4:22:6F";
    private String pelota="A3:C4:A2:23:11";
    private String botella="W0:Y8:K6:03:78";
    private String rompecabezas="Q4:Z2:I3:04:03";

    private String imgtorre="https://firebasestorage.googleapis.com/v0/b/rehabilitacioniot-a9f5a.appspot.com/o/dispotivos%2Fistockphoto-1083263642-612x612.jpg?alt=media&token=c0bcd3ad-418d-4647-ab92-3a6234fe80b0";
    private String imgpelota="https://firebasestorage.googleapis.com/v0/b/rehabilitacioniot-a9f5a.appspot.com/o/dispotivos%2Fdise%C3%B1o-del-logo-de-la-pelota-f%C3%BAtbol-icono-vector-ilustraci%C3%B3n-vectorial-184321567.jpg?alt=media&token=299a2ef7-91bf-41f9-b02e-4c8bbe74a08f";
    private String imgbotella="https://firebasestorage.googleapis.com/v0/b/rehabilitacioniot-a9f5a.appspot.com/o/dispotivos%2FIMG_20210301_212720.png?alt=media&token=6139b0ff-a2bd-4473-90d0-e5b98125c5b3";
    private String imgrompecabezas="";

    public adpExercise(List<Ejercicios> exerciseList, Context mCtx) {
        this.exerciseList = exerciseList;
        Ctx=mCtx;
    }

    @NonNull
    @Override
    public adpExercise.ViewHolderE onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.item_therapies, null);
        return new adpExercise.ViewHolderE(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adpExercise.ViewHolderE holder, int position) {

        Ejercicios exerciseModel = exerciseList.get(position);
        holder.txtTherapyType.setText(exerciseModel.getTypetherapy());

        holder.txtDate.setText(exerciseModel.getDate());
        holder.txtInteractions.setText(exerciseModel.getInteractions());

        String dispositivo= "",imgd="";

        if (exerciseModel.getMac().equals("0")){
            dispositivo= torre;
            imgd = imgtorre;
        }
        if (exerciseModel.getMac().equals("1")){
            dispositivo= pelota;
            imgd = imgpelota;
        }
        if (exerciseModel.getMac().equals("2")){
            dispositivo= botella;
            imgd = imgbotella;
        }
        if (exerciseModel.getMac().equals("3")){
            dispositivo= rompecabezas;
            imgd = imgrompecabezas;
        }

        holder.txtMac.setText(dispositivo);

        Glide.with(Ctx)
                .load(imgd)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public class ViewHolderE extends RecyclerView.ViewHolder{
        private TextView txtTherapyType;
        private TextView txtMac;
        private TextView txtDate;
        private TextView txtInteractions;
        private ImageView img;

        public View view;

        public ViewHolderE(View view){
            super(view);
            this.txtTherapyType = (TextView) view.findViewById(R.id.txtTherapyType);
            this.txtMac = (TextView) view.findViewById(R.id.txtMac);
            this.txtDate = (TextView) view.findViewById(R.id.txtDate);
            this.txtInteractions = (TextView) view.findViewById(R.id.txtInteractions);
            this.img = view.findViewById(R.id.imgDispositive);
        }
    }
}
