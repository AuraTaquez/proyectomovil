package com.example.myapplicationfin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import clases.Usuario;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference refcollection = db.collection("users");
    public ArrayList<Usuario> datauser = new ArrayList<>();
    Toolbar toolbar;
    TextView txtUser;
    TextView txtPass;
    Button btnIniciar;
    String id="", address="", birthdate="", cid="", email="", pass = "", names="", surnames="", phone="", role = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        txtUser=(TextView) findViewById(R.id.et_usuario);
        txtPass=(TextView) findViewById(R.id.contraseña);

        btnIniciar=(Button) findViewById(R.id.iniciar_sesion);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtUser.getText().toString().isEmpty() && !txtPass.getText().toString().isEmpty()) {
                    refcollection.whereEqualTo("email", txtUser.getText().toString())
                            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException error) {
                                    if (error != null) {
                                        System.err.println("Error al consultar: " + error);
                                        return;
                                    }
                                    int cont = 0;
                                    for (DocumentChange doc : snapshots.getDocumentChanges()) {
                                        cont++;
                                        id=doc.getDocument().getId();
                                        address = doc.getDocument().get("address").toString();
                                        birthdate = doc.getDocument().get("birthdate").toString();
                                        cid = doc.getDocument().get("cid").toString();
                                        email = doc.getDocument().get("email").toString();
                                        pass = doc.getDocument().get("password").toString();
                                        names = doc.getDocument().get("names").toString();
                                        surnames = doc.getDocument().get("surnames").toString();
                                        phone = doc.getDocument().get("phone").toString();
                                        role = doc.getDocument().get("role").toString();
                                        datauser.add(new Usuario(address, birthdate, cid, email, pass, names, surnames, phone, role));
                                    }
                                    //Toast.makeText(MainActivity.this,"He encotrado "+cont+"usuarios",Toast.LENGTH_LONG).show();
                                    if (cont == 1) {
                                        if (txtPass.getText().toString().equals(pass))
                                            iniciar(role);
                                        else
                                            Toast.makeText(MainActivity.this, "Advertencia \nClave Incorrecta ", Toast.LENGTH_LONG).show();
                                    } else
                                        Toast.makeText(MainActivity.this, "Advertencia \nUsuario no encontrado", Toast.LENGTH_LONG).show();
                                }
                            });
                }
                else
                    Toast.makeText(MainActivity.this,"Advertencia \nDebe llenar todos los campos",Toast.LENGTH_LONG).show();
            }
        });
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

    public void iniciar(String tipo)
    {
        if(tipo.equals("A"))
        {
            Intent intent = new Intent(this, admin.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("id", id);
            startActivity(intent);
        }
        else if (tipo.equals("T"))
        {
            Intent intent = new Intent(this, terapeuta.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("id", id);
            startActivity(intent);
        }
        else if (tipo.equals("P"))
        {
            Intent intent = new Intent(this, paciente.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("id", id);
            startActivity(intent);
        }
    }
}