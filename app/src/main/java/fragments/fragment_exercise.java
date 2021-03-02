package fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.example.myapplicationfin.R;
import com.example.myapplicationfin.activity_patient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapters.adpExercise;
import clases.Ejercicios;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_exercise#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_exercise extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference refDataBase,refDataBaseEj;
    public ArrayList<Ejercicios> dataEjercicios = new ArrayList<>();
    String id,typetherapy, mac, date, interactions,id_user;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_exercise() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_exercise.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_exercise newInstance(String param1, String param2) {
        fragment_exercise fragment = new fragment_exercise();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        refDataBase = FirebaseDatabase.getInstance().getReference("prescription");
        refDataBaseEj = FirebaseDatabase.getInstance().getReference("prescription");
        id_user = this.getArguments().getString("id");
        Exercise();
        return view;
    }

    void Exercise(){

        Query query = refDataBase.orderByChild("iduser").equalTo(id_user);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot : snapshot.getChildren()) {

                    id = datasnapshot.getKey();
                    typetherapy = datasnapshot.child("typetheraphy").getValue().toString();
                    mac = datasnapshot.child("iddevice").getValue().toString();
                    date = datasnapshot.child("Header").getValue().toString();
                    int posi = 0, posf = 0, cantdate = 0;
                    String r = date;
                    cantdate = contarCaracteres(date,"={");
                    for(int i=0; i<cantdate; i++){
                        posi = r.indexOf("={");
                        posf = r.indexOf("},");
                        if (posf == -1){
                            posf = r.indexOf("}}");
                        }
                        if(i==0)
                            date =  r.substring(1,posi);
                        else
                            date =  r.substring(2,posi);


                        String result = r.substring(posi+1,posf);
                        r = r.substring(posf+1, r.length());

                        String auxr="";
                        if (mac.equals("0")){
                            int a;
                            a = result.indexOf("Time");
                            auxr =  result.substring(a+5,a+7)+" seg";
                        }
                        if(mac.equals("1"))
                            auxr = " Interactions";

                        int cant_inte = contarCaracteres(result,"=");
                        //if (mac.equals("1"))
                            cant_inte--;
                        interactions = String.valueOf(cant_inte)+" - "+auxr;
                        dataEjercicios.add(new Ejercicios(id,typetherapy,mac,date,interactions));
                    }
                }
                adpExercise adapatorE = new adpExercise(dataEjercicios,getContext());
                recyclerView.setAdapter(adapatorE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });

        int resId = R.anim.layout_animation_down_to_up;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(), resId);
        recyclerView.setLayoutAnimation(animation);
    }


    public static int contarCaracteres(String cadena,String caracter) {
        int posicion, contador = 0;
        posicion = cadena.indexOf(caracter);
        while (posicion != -1) {
            contador++;
            posicion = cadena.indexOf(caracter, posicion + 1);
        }
        return contador;
    }

}