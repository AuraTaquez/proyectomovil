package fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplicationfin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import adapters.adpDispositive;
import adapters.adpPrescription;
import clases.DispositiveModel;
import clases.PrescriptionModel;
import clases.UserModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_prescription#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_prescription extends Fragment {

    private DatabaseReference refDataBase;
    private FirebaseFirestore refFireStore;

    private DatabaseReference refDataUser;
    private DatabaseReference refDataDevice;

    private adpPrescription mAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<PrescriptionModel> mPrescriptionList = new ArrayList<>();

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


        refDataBase = FirebaseDatabase.getInstance().getReference();
        refFireStore = FirebaseFirestore.getInstance();

        refDataUser = FirebaseDatabase.getInstance().getReference();
        refDataDevice = FirebaseDatabase.getInstance().getReference();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rcvPrescription);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getPrescriptionFromFireBase();
        return view;
    }

    private void getPrescriptionFromFireBase(){
        ArrayList<DispositiveModel> mDispositivesList  = getAllDevicesFromFireBase();
        ArrayList<UserModel> mUserList = getAllUserFromFireBase();
        refDataBase.child("prescription").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    mPrescriptionList.clear();
                    for(DataSnapshot ds: snapshot.getChildren()){
                        String ID = ds.getKey();
                        String iduser = ds.child("iduser").getValue().toString();
                        String iddevice = ds.child("iddevice").getValue().toString();
                        String status = ds.child("status").getValue().toString();
                        String typetherapy = ds.child("typetherapy").getValue().toString();
                        String commentary = ds.child("commentary").getValue().toString();
                        String date = ds.child("date").getValue().toString();
                        String pacient = "";
                        for(int a = 0; a < mUserList.size(); a++){
                            UserModel userModel = mUserList.get(a);
                            if(userModel.getId().equals(iduser)){
                                pacient = userModel.getNames() + " " + userModel.getSurnames();
                            }
                        }
                        String dispositivo = "";
                        for(int a = 0; a < mDispositivesList.size(); a++){
                            DispositiveModel dispositiveModel = mDispositivesList.get(a);
                            if(dispositiveModel.getId().equals(iddevice)){
                                dispositivo = dispositiveModel.getDispositive();
                            }
                        }
                        mPrescriptionList.add(new PrescriptionModel(ID, pacient, dispositivo, status, typetherapy, commentary, date));
                    }
                    mAdapter = new adpPrescription(mPrescriptionList, R.layout.item_prescription);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private ArrayList<DispositiveModel>  getAllDevicesFromFireBase(){
        ArrayList<DispositiveModel> mDispositivesList  = new ArrayList<>();
        refDataDevice.child("device").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    mDispositivesList.clear();
                    for(DataSnapshot ds: snapshot.getChildren()){
                        String ID = ds.getKey();
                        String MAC = ds.child("mac").getValue().toString();
                        String dispositive = ds.child("device").getValue().toString();
                        String state = ds.child("status").getValue().toString();
                        String observation = ds.child("observation").getValue().toString();
                        mDispositivesList.add(new DispositiveModel(ID, MAC, dispositive, state, observation));
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return mDispositivesList;
    }

    private ArrayList<UserModel>  getAllUserFromFireBase(){
        ArrayList<UserModel> mUserList  = new ArrayList<>();
        refDataUser.child("usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    mUserList.clear();
                    for(DataSnapshot ds: snapshot.getChildren()){
                        String id = ds.getKey();
                        String address = ds.child("address").getValue().toString();
                        String birthdate = ds.child("birthdate").getValue().toString();
                        String cid = ds.child("cid").getValue().toString();
                        String email = ds.child("email").getValue().toString();
                        String pass = ds.child("password").getValue().toString();
                        String names = ds.child("names").getValue().toString();
                        String surnames = ds.child("lastnames").getValue().toString();
                        String phone = ds.child("phone").getValue().toString();
                        String role = ds.child("role").getValue().toString();
                        mUserList.add(new UserModel(id, address, birthdate, cid, email, pass, names, surnames, phone, role));
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return mUserList;
    }
}