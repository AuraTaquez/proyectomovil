package com.example.myapplicationfin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import adapters.adpExercise;
import adapters.adpPrescription;
import clases.Ejercicios;
import clases.PrescriptionModel;
import fragments.fragment_exercise;
import fragments.fragment_myinfo;
import fragments.fragment_prescription;
import fragments.fragment_therapies;
import fragments.fragment_video;

public class activity_patient extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    Bundle b = new Bundle();
    DrawerLayout drawerLayout;
    NavigationView navView;
    Toolbar toolbar;
    Fragment fragment;
    boolean fragmentTransaction;
    TextView txtUserFooter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        b = this.getIntent().getExtras();

        sendDataActivity();

        toolbar = findViewById(R.id.toolbar_patient);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navView = findViewById(R.id.nav_view);
        Menu m = navView.getMenu();
        m.findItem(R.id.Umenu_section_2).setIcon(R.drawable.icon_see_progress).setTitle("My Prescription");
        m.findItem(R.id.Umenu_section_3).setIcon(R.drawable.ic_baseline_fitness_center_24).setTitle("My Exercise");
        m.findItem(R.id.Umenu_section_4).setIcon(R.drawable.ic_baseline_ondemand_video_24).setTitle("Demonstration Exercise");
        navView.setNavigationItemSelectedListener(this);

        txtUserFooter = findViewById(R.id.txtUserFooter);
        txtUserFooter.setText(b.getString("email"));

    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        fragmentTransaction = false;
        fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.Umenu_section_1:
                fragment = new fragment_myinfo();
                fragment.setArguments(b);
                fragmentTransaction = true;
                break;
            case R.id.Umenu_section_2:
                fragment = new fragment_prescription();
                fragment.setArguments(b);
                fragmentTransaction = true;
                break;

            case R.id.Umenu_section_3:
                fragment = new fragment_exercise();
                fragment.setArguments(b);
                fragmentTransaction = true;
                break;

            case R.id.Umenu_section_4:
                fragment = new fragment_video();
                fragment.setArguments(b);
                fragmentTransaction = true;
                break;
        }
        if(fragmentTransaction) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_patient, fragment).commit();
            menuItem.setChecked(true);
            getSupportActionBar().setTitle("");
        }
        drawerLayout.closeDrawers();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar2,menu);
        MenuItem m = menu.findItem(R.id.btnType);
        m.setTitle("PATIENT");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout_patient);
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        if(id == R.id.btnSign_off) {
            Intent intent = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void sendDataActivity(){
        b.putString("id", b.getString("id"));
        b.putString("cid", b.getString("cid"));
        b.putString("address", b.getString("address"));
        b.putString("birthdate", b.getString("birthdate"));
        b.putString("email", b.getString("email"));
        b.putString("pass", b.getString("pass"));
        b.putString("names", b.getString("names"));
        b.putString("surnames", b.getString("surnames"));
        b.putString("phone", b.getString("phone"));
        b.putString("role", b.getString("role"));
    }

}