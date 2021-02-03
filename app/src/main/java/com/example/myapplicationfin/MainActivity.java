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

import clases.UserModel;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference refcollection = db.collection("users");
    public ArrayList<UserModel> datauser = new ArrayList<>();
    Toolbar toolbar;
    TextView txtUser;
    TextView txtPass;
    Button btnLogin;
    String id="", address="", birthdate="", cid="", email="", pass = "", names="", surnames="", phone="", role = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        txtUser=(TextView) findViewById(R.id.txtUser);
        txtPass=(TextView) findViewById(R.id.txtPassword);

        btnLogin=(Button) findViewById(R.id.btn_log_in);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtUser.getText().toString().isEmpty() && !txtPass.getText().toString().isEmpty()) {
                    refcollection.whereEqualTo("email", txtUser.getText().toString())
                            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot snapshots, @Nullable FirebaseFirestoreException error) {
                                    if (error != null) {
                                        System.err.println("Query error: " + error);
                                        Toast.makeText(MainActivity.this, "Query error: " + error, Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                    int count = 0;
                                    for (DocumentChange doc : snapshots.getDocumentChanges()) {
                                        count++;
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
                                        datauser.add(new UserModel(address, birthdate, cid, email, pass, names, surnames, phone, role));
                                    }
                                    //Toast.makeText(MainActivity.this,"I have found "+count+" users",Toast.LENGTH_LONG).show();
                                    if (count == 1) {
                                        if (txtPass.getText().toString().equals(pass))
                                            init(role);
                                        else
                                            Toast.makeText(MainActivity.this, "WARNING \nIncorrect password", Toast.LENGTH_LONG).show();
                                    } else
                                        Toast.makeText(MainActivity.this, "WARNING \nUser not found", Toast.LENGTH_LONG).show();
                                }
                            });
                }
                else
                    Toast.makeText(MainActivity.this,"WARNING \nYou must fill all the fields",Toast.LENGTH_LONG).show();
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

    public void init(String type)
    {
        if(type.equals("A"))
        {
            Intent intent = new Intent(this, activity_admin.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("id", id);
            startActivity(intent);
        }
        else if (type.equals("T"))
        {
            Intent intent = new Intent(this, activity_therapist.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("id", id);
            startActivity(intent);
        }
        else if (type.equals("P"))
        {
            Intent intent = new Intent(this, activity_patient.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("id", id);
            startActivity(intent);
        }
    }
}