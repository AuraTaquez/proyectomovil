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

import com.example.myapplicationfin.MainActivity;
import com.example.myapplicationfin.R;
import com.example.myapplicationfin.WebService.Asynchtask;
import com.example.myapplicationfin.WebService.WebService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import adapters.adpDispositive;
import adapters.adpPrescription;
import clases.DispositiveModel;
import clases.PrescriptionModel;
import clases.UserModel;

import static clases.Encriptar.SHA1;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_prescription#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_prescription extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference refDataBase;
    public ArrayList<PrescriptionModel> dataPrescription = new ArrayList<>();
    String id,typetheraphy,device,date,status,commentary;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_prescription() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_prescription.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_prescription newInstance(String param1, String param2) {
        fragment_prescription fragment = new fragment_prescription();
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
        View view = inflater.inflate(R.layout.fragment_prescription, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        refDataBase = FirebaseDatabase.getInstance().getReference("prescription");
        Prescription();
        return view;
    }


    public void Prescription() {
        String id_user = this.getArguments().getString("id");
        Toast.makeText(getActivity(),id_user,Toast.LENGTH_LONG).show();

        Query query = refDataBase.orderByChild("iduser").equalTo(id_user);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                    id = datasnapshot.getKey();
                    typetheraphy = datasnapshot.child("typetheraphy").getValue().toString();
                    device = datasnapshot.child("iddevice").getValue().toString();
                    date = datasnapshot.child("date").getValue().toString();
                    status = datasnapshot.child("status").getValue().toString();
                    commentary = datasnapshot.child("commentary").getValue().toString();
                    dataPrescription.add(new PrescriptionModel(id,typetheraphy,device,date,status,commentary));
                }
                adpPrescription adapatorPrescription = new adpPrescription(dataPrescription,getContext());
                recyclerView.setAdapter(adapatorPrescription);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });


        int resId = R.anim.layout_animation_down_to_up;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getActivity(), resId);
        recyclerView.setLayoutAnimation(animation);

    }
}