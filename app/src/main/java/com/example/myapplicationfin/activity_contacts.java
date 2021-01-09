package com.example.myapplicationfin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import adaptadores.adpParticipante;
import clases.Participante;

public class activity_contacts extends AppCompatActivity {

    RecyclerView rcvParticipantes;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        rcvParticipantes = findViewById(R.id.rcvParticipantes);
        LinearLayoutManager linear = new LinearLayoutManager(getApplicationContext());
        linear.setOrientation(LinearLayoutManager.VERTICAL);
        rcvParticipantes.setLayoutManager(linear);
        rcvParticipantes.hasFixedSize();

        adpParticipante.showShimmer = true;

        List<Participante> lista = new ArrayList<Participante>();
        final adpParticipante adpParticipante = new adpParticipante(lista);
        rcvParticipantes.setAdapter(adpParticipante);

        Participante p1 = new Participante("Burbano Parraga Cristhian","+593980395656","cristhian.burbano2016@uteq.edu.ec");
        Participante p2 = new Participante("Carvajal Florencia Carlos","+593979094938","carlos.carvajal2015@uteq.edu.ec");
        Participante p3 = new Participante("Guerrero Moreira Rafael","+593979094938","rafael.guerrero2016@uteq.edu.ec");
        Participante p4 = new Participante("Jaya Puruncaja Ruben","+593979094938","ruben.jaya2015@uteq.edu.ec");
        Participante p5 = new Participante("Moreira Torres Daniela","+593979094938","daniela.moreira2015@uteq.edu.ec");
        Participante p6 = new Participante("Ordoñez Guerrero Karina","+593979094938","karina.ordonez2016@uteq.edu.ec");
        Participante p7 = new Participante("Taquez Suarez Aura","+593979094938","aura.taquez2015@uteq.edu.ec");
        Participante p8 = new Participante("Zapata Espinoza Victor","+593979094938","victor.zapata2016@uteq.edu.ec");

        final List<Participante> finalLista = new ArrayList<Participante>();
        finalLista.add(p1);
        finalLista.add(p2);
        finalLista.add(p3);
        finalLista.add(p4);
        finalLista.add(p5);
        finalLista.add(p6);
        finalLista.add(p7);
        finalLista.add(p8);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adpParticipante adpParticipantes = new adpParticipante(finalLista);
                rcvParticipantes.setAdapter(adpParticipantes);
                adpParticipantes.showShimmer = false;
                adpParticipantes.notifyDataSetChanged();
            }
        }, 1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutoolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.btnInformacion) {
            Intent intent = new Intent(this, activity_information.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
        if(id == R.id.btnContactos) {
            Intent intent = new Intent(this, activity_contacts.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}