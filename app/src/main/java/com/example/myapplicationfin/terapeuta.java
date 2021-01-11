package com.example.myapplicationfin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class terapeuta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terapeuta);

        ImageButton info = findViewById(R.id.btnRegistrarPaciente);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), registrar_paciente.class);
                startActivityForResult(intent, 0);
            }
        });

        TextView registrarPaciente = findViewById(R.id.txtRegistrarPaciente);
        registrarPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), registrar_paciente.class);
                startActivityForResult(intent, 0);
            }
        });

    }
}