package com.example.myapplicationfin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

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

        toolbar1 = findViewById(R.id.toolbarazul);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navView = findViewById(R.id.nav_viewadmin);
        navView.setNavigationItemSelectedListener(this);
          }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        fragmentTransaction = false;
        int id=menuItem.getItemId();
        fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.Pmenu_admin_1:


                break;
            case R.id.Pmenu_admin_2:


                break;
            case R.id.Pmenu_admin_3:

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
        getMenuInflater().inflate(R.menu.menutoolbaradmin,menuadmin);
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
        if(id == R.id.btnCerrraradmin) {
            Intent intent = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
        }
