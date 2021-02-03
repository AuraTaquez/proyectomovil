package com.example.myapplicationfin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

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

public class activity_dispositives extends AppCompatActivity {

    private DatabaseReference refDataBase;
    private FirebaseFirestore refFireStore;
    EditText txtMensaje;
    Button btnEnviar;

    private adpDispositive mAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<DispositiveModel> mDispositivesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivos);

        refDataBase = FirebaseDatabase.getInstance().getReference();
        refFireStore = FirebaseFirestore.getInstance();

        mRecyclerView = (RecyclerView) findViewById(R.id.rcvDispositives);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        getDispositivosFromFireStore();
    }

    private void getDispositivosFromFireStore(){
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
                            mAdapter = new adpDispositive(mDispositivesList, R.layout.dispositivos_view);
                            mRecyclerView.setAdapter(mAdapter);
                        } else {
                            //Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }


    private void getDispositivosFromFireBase(){
        refDataBase.child("dispositivos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    mDispositivesList.clear();
                    for(DataSnapshot ds: snapshot.getChildren()){
                        //String ID = ds.getKey();
                        String MAC = ds.child("MAC").getValue().toString();
                        String dispositive = ds.child("dispositivo").getValue().toString();
                        String state = ds.child("estado").getValue().toString();
                        String observation = ds.child("observaciones").getValue().toString();
                        mDispositivesList.add(new DispositiveModel("0", MAC, dispositive, state, observation));

                    }
                    mAdapter = new adpDispositive(mDispositivesList, R.layout.dispositivos_view);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}