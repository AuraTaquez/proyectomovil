package com.example.myapplicationfin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import fragments.fragment_myinfo;

public class activity_therapist extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navView;
    Toolbar toolbar;
    Fragment fragment;
    boolean fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist);

        toolbar = findViewById(R.id.toolbar_therapist);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navView = findViewById(R.id.nav_view_therapist);
        Menu m = navView.getMenu();
        m.findItem(R.id.Umenu_section_2).setIcon(R.drawable.icon_patients).setTitle("Patients");
        m.findItem(R.id.Umenu_section_3).setIcon(R.drawable.icon_see_progress).setTitle("Prescriptions");
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        fragmentTransaction = false;
        fragment = null;
        switch (item.getItemId()) {
            case R.id.Umenu_section_1:
                fragment = new fragment_myinfo();
                fragmentTransaction = true;
                break;
            case R.id.Umenu_section_2:

                break;
            case R.id.Umenu_section_3:

                break;
        }
        if(fragmentTransaction) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_therapist, fragment).commit();
            item.setChecked(true);
            getSupportActionBar().setTitle("");
        }
        drawerLayout.closeDrawers();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar2,menu);
        MenuItem m = menu.findItem(R.id.btnType);
        m.setTitle("THERAPIST");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout_therapist);
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        if(id == R.id.btnSign_off) {
            Intent intent = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}