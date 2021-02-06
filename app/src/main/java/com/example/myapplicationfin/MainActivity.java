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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import clases.UserModel;

import static clases.Encriptar.SHA1;

public class MainActivity extends AppCompatActivity {

    DatabaseReference refDataBase;
    FirebaseFirestore refFireStore;
    CollectionReference refCollection;
    public ArrayList<UserModel> datauser = new ArrayList<>();
    Bundle b = new Bundle();
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

        refDataBase = FirebaseDatabase.getInstance().getReference("usuario");
        refFireStore = FirebaseFirestore.getInstance();
        refCollection = refFireStore.collection("users");

        txtUser=(TextView) findViewById(R.id.txtUser);
        txtPass=(TextView) findViewById(R.id.txtPassword);

        btnLogin=(Button) findViewById(R.id.btn_log_in);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //queryInitFireStore();
                queryInitRealTime();
            }
        });
    }

    public void queryInitFireStore(){
        if(!txtUser.getText().toString().isEmpty() && !txtPass.getText().toString().isEmpty()) {
            refCollection.whereEqualTo("email", txtUser.getText().toString())
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
                                datauser.add(new UserModel(id, address, birthdate, cid, email, pass, names, surnames, phone, role));
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

    public void queryInitRealTime(){
        if(!txtUser.getText().toString().isEmpty() && !txtPass.getText().toString().isEmpty()) {
            Query query = refDataBase.orderByChild("email").equalTo(txtUser.getText().toString());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int count = 0;
                    for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                        count++;
                        //doc.getDocument().getId();
                        id = datasnapshot.getKey();
                        address = datasnapshot.child("address").getValue().toString();
                        birthdate = datasnapshot.child("birthdate").getValue().toString();
                        cid = datasnapshot.child("cid").getValue().toString();
                        email = datasnapshot.child("email").getValue().toString();
                        pass = datasnapshot.child("password").getValue().toString();
                        names = datasnapshot.child("names").getValue().toString();
                        surnames = datasnapshot.child("lastnames").getValue().toString();
                        phone = datasnapshot.child("phone").getValue().toString();
                        role = datasnapshot.child("role").getValue().toString();
                        datauser.add(new UserModel(id, address, birthdate, cid, email, pass, names, surnames, phone, role));
                    }
                    //Toast.makeText(MainActivity.this,"I have found "+count+" users",Toast.LENGTH_LONG).show();
                    if(count == 1){
                        try {
                            if(SHA1(txtPass.getText().toString()).equals(pass))
                                init(role);
                            else
                                Toast.makeText(MainActivity.this,"WARNING \nIncorrect password",Toast.LENGTH_LONG).show();
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                        Toast.makeText(MainActivity.this,"WARNING \nUser not found",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else
            Toast.makeText(MainActivity.this,"WARNING \nYou must fill all the fields",Toast.LENGTH_LONG).show();
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

        Intent intent = new Intent(this, activity_patient.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        sendDataActivity();
        intent.putExtras(b);
        startActivity(intent);
        /*
        if(type.equals("A"))
        {
            Intent intent = new Intent(this, activity_admin.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            sendDataActivity();
            intent.putExtras(b);
            startActivity(intent);
        }
        else if (type.equals("T"))
        {
            Intent intent = new Intent(this, activity_therapist.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            sendDataActivity();
            intent.putExtras(b);
            startActivity(intent);
        }
        else if (type.equals("P"))
        {
            Intent intent = new Intent(this, activity_patient.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            sendDataActivity();
            intent.putExtras(b);
            startActivity(intent);
        }*/
    }

    public void sendDataActivity(){
        b.putString("id", id);
        b.putString("cid", cid);
        b.putString("address", address);
        b.putString("birthdate", birthdate);
        b.putString("email", email);
        b.putString("pass", pass);
        b.putString("names", names);
        b.putString("surnames", surnames);
        b.putString("phone", phone);
        b.putString("role", role);
    }
}