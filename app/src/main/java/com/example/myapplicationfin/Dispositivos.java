package com.example.myapplicationfin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import adaptadores.DispositivoAdapter;
import clases.DispositivosModel;

public class Dispositivos extends AppCompatActivity {

    private DatabaseReference refDataBase;
    EditText txtMensaje;
    Button btnEnviar;

    private DispositivoAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<DispositivosModel> mDispositivossList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivos);

        refDataBase = FirebaseDatabase.getInstance().getReference();

        mRecyclerView = (RecyclerView) findViewById(R.id.rvDispositivos);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        getDispositivosFromFireBase();
    }


    private void getDispositivosFromFireBase(){
        refDataBase.child("dispositivos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    mDispositivossList.clear();
                    for(DataSnapshot ds: snapshot.getChildren()){
                        //String ID = ds.getKey();
                        String MAC = ds.child("MAC").getValue().toString();
                        String dispositivo = ds.child("dispositivo").getValue().toString();
                        String estado = ds.child("estado").getValue().toString();
                        String observacion = ds.child("observaciones").getValue().toString();
                        mDispositivossList.add(new DispositivosModel("0", MAC, dispositivo, estado, observacion));

                    }
                    mAdapter = new DispositivoAdapter(mDispositivossList, R.layout.dispositivos_view);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}