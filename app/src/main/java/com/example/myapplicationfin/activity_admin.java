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

import fragments.fragment_dispositives;
import fragments.fragment_myinfo;

public class activity_admin extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    Bundle b = new Bundle();
    DrawerLayout drawerLayout;
    NavigationView navView;
    Fragment fragment;
    boolean fragmentTransaction;

    Toolbar toolbar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        b = this.getIntent().getExtras();
        sendDataActivity();

        toolbar1 = findViewById(R.id.toolbar_admin);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navView = findViewById(R.id.nav_viewadmin);
        Menu m = navView.getMenu();
        m.findItem(R.id.Umenu_section_2).setIcon(R.drawable.icon_therapists).setTitle("Therapists");
        m.findItem(R.id.Umenu_section_3).setIcon(R.drawable.icon_dispositives).setTitle("Dispositives");
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        fragmentTransaction = false;
        int id=menuItem.getItemId();
        fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.Umenu_section_1:
                fragment = new fragment_myinfo();
                fragment.setArguments(b);
                fragmentTransaction = true;
                break;
            case R.id.Umenu_section_2:

                break;
            case R.id.Umenu_section_3:
                fragment = new fragment_dispositives();
                fragmentTransaction = true;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + menuItem.getItemId());
        }
        if (fragmentTransaction) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_administrator, fragment).commit();
            menuItem.setChecked(true);
            getSupportActionBar().setTitle("");
        }
        drawerLayout.closeDrawers();
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menuadmin) {
        getMenuInflater().inflate(R.menu.menu_toolbar2,menuadmin);
        MenuItem m = menuadmin.findItem(R.id.btnType);
        m.setTitle("ADMINISTRATOR");
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
