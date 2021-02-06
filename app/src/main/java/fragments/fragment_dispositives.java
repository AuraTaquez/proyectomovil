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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import adapters.adpDispositive;
import clases.DispositiveModel;

public class fragment_dispositives extends Fragment {

    private DatabaseReference refDataBase;
    private FirebaseFirestore refFireStore;

    private adpDispositive mAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<DispositiveModel> mDispositivesList = new ArrayList<>();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public fragment_dispositives() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_dispositives.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_dispositives newInstance(String param1, String param2) {
        fragment_dispositives fragment = new fragment_dispositives();
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
        View view = inflater.inflate(R.layout.fragment_dispositives, container, false);

        refDataBase = FirebaseDatabase.getInstance().getReference();
        refFireStore = FirebaseFirestore.getInstance();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rcvDispositives);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getDispositivesFromFireBase();
        //getDispositivesFromFireStore();
        return view;
    }

    private void getDispositivesFromFireStore(){
        refFireStore.collection("dispositivos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            mDispositivesList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String MAC = document.getString("mac");
                                String dispositive = document.getString("device");
                                String state = document.getString("estado");
                                String observation = document.getString("observation");
                                mDispositivesList.add(new DispositiveModel("0", MAC, dispositive, state, observation));
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                            mAdapter = new adpDispositive(mDispositivesList, R.layout.item_dispositives);
                            mRecyclerView.setAdapter(mAdapter);
                        } else {
                            //Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void getDispositivesFromFireBase(){
        refDataBase.child("device").addValueEventListener(new ValueEventListener() {
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
                    mAdapter = new adpDispositive(mDispositivesList, R.layout.item_dispositives);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}