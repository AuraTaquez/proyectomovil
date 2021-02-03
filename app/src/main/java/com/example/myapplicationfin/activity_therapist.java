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

import fragments.fragment_miinfo;

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
        setContentView(R.layout.activity_terapeuta);

        toolbar = findViewById(R.id.toolbar_terapeuta);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navView = findViewById(R.id.nav_view_terapeuta);
        Menu m = navView.getMenu();
        m.findItem(R.id.Umenu_seccion_2).setIcon(R.drawable.icon_patients).setTitle("Pacientes");
        m.findItem(R.id.Umenu_seccion_3).setIcon(R.drawable.icon_see_progress).setTitle("Prescripciones");
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        fragmentTransaction = false;
        fragment = null;
        switch (item.getItemId()) {
            case R.id.Umenu_seccion_1:
                fragment = new fragment_miinfo();
                fragmentTransaction = true;
                break;
            case R.id.Umenu_seccion_2:

                break;
            case R.id.Umenu_seccion_3:

                break;
        }
        if(fragmentTransaction) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenido_terapeuta, fragment).commit();
            item.setChecked(true);
            getSupportActionBar().setTitle("");
        }
        drawerLayout.closeDrawers();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar2,menu);
        MenuItem m = menu.findItem(R.id.btnTipo);
        m.setTitle("TERAPEUTA");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout_terapeuta);
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        if(id == R.id.btnCerrarSesion) {
            Intent intent = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}