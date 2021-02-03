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

import adapters.adpParticipant;
import clases.ParticipantModel;

public class activity_contacts extends AppCompatActivity {

    RecyclerView rcvParticipants;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        rcvParticipants = findViewById(R.id.rcvParticipants);
        LinearLayoutManager linear = new LinearLayoutManager(getApplicationContext());
        linear.setOrientation(LinearLayoutManager.VERTICAL);
        rcvParticipants.setLayoutManager(linear);
        rcvParticipants.hasFixedSize();

        adpParticipant.showShimmer = true;

        List<ParticipantModel> lista = new ArrayList<ParticipantModel>();
        final adpParticipant adpParticipant = new adpParticipant(lista);
        rcvParticipants.setAdapter(adpParticipant);

        ParticipantModel p1 = new ParticipantModel("Burbano Parraga Cristhian","+593980395656","cristhian.burbano2016@uteq.edu.ec");
        ParticipantModel p2 = new ParticipantModel("Carvajal Florencia Carlos","+593979094938","carlos.carvajal2015@uteq.edu.ec");
        ParticipantModel p3 = new ParticipantModel("Guerrero Moreira Rafael","+593979094938","rafael.guerrero2016@uteq.edu.ec");
        ParticipantModel p4 = new ParticipantModel("Jaya Puruncaja Ruben","+593979094938","ruben.jaya2015@uteq.edu.ec");
        ParticipantModel p5 = new ParticipantModel("Moreira Torres Daniela","+593979094938","daniela.moreira2015@uteq.edu.ec");
        ParticipantModel p6 = new ParticipantModel("Ordoñez Guerrero Karina","+593979094938","karina.ordonez2016@uteq.edu.ec");
        ParticipantModel p7 = new ParticipantModel("Taquez Suarez Aura","+593979094938","aura.taquez2015@uteq.edu.ec");
        ParticipantModel p8 = new ParticipantModel("Zapata Espinoza Victor","+593979094938","victor.zapata2016@uteq.edu.ec");

        final List<ParticipantModel> finalList = new ArrayList<ParticipantModel>();
        finalList.add(p1);
        finalList.add(p2);
        finalList.add(p3);
        finalList.add(p4);
        finalList.add(p5);
        finalList.add(p6);
        finalList.add(p7);
        finalList.add(p8);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adpParticipant adpParticipants = new adpParticipant(finalList);
                rcvParticipants.setAdapter(adpParticipants);
                adpParticipants.showShimmer = false;
                adpParticipants.notifyDataSetChanged();
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
        if(id == R.id.btnInformation) {
            Intent intent = new Intent(this, activity_information.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
        if(id == R.id.btnContacts) {
            Intent intent = new Intent(this, activity_contacts.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}