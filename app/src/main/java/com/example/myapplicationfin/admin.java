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

public class admin extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navView;
    Fragment fragment;
    boolean fragmentTransaction;

    Toolbar toolbar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        toolbar1 = findViewById(R.id.toolbar_admin);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navView = findViewById(R.id.nav_viewadmin);
        Menu m = navView.getMenu();
        m.findItem(R.id.Umenu_seccion_2).setIcon(R.drawable.icon_terapeutas).setTitle("Terapeutas");
        m.findItem(R.id.Umenu_seccion_3).setIcon(R.drawable.icon_dispositivos).setTitle("Dispositivos");
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        fragmentTransaction = false;
        int id=menuItem.getItemId();
        fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.Umenu_seccion_1:
                fragment = new fragment_miinfo();
                fragmentTransaction = true;
                break;
            case R.id.Umenu_seccion_2:


                break;
            case R.id.Umenu_seccion_3:

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + menuItem.getItemId());
        }
        if (fragmentTransaction) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenido_administrador, fragment).commit();
            menuItem.setChecked(true);
            getSupportActionBar().setTitle(menuItem.getTitle());
        }
        drawerLayout.closeDrawers();
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menuadmin) {
        getMenuInflater().inflate(R.menu.menu_toolbar2,menuadmin);
        MenuItem m = menuadmin.findItem(R.id.btnTipo);
        m.setTitle("ADMINISTRADOR");
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout_admin);
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
