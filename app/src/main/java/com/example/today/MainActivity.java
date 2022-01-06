package com.example.today;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import com.example.today.apiclient.TaskInterface;
import com.example.today.history.HistoryActivity;
import com.example.today.recommendation.RecommendationFragment;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TaskInterface taskInterface;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    static String KEY_USER = "nama_user";
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerlayout);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        setTitle("");

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar
        ,R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
//        Navigation Bar
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_aktivitas);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new MainFragment()).commit();

        }
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_aktivitas:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new MainFragment()).commit();
//                navigationView.setCheckedItem(R.id.nav_aktivitas);
                break;
            case R.id.nav_recommendation:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new RecommendationFragment()).commit();
//                navigationView.setCheckedItem(R.id.nav_recommendation);
                break;
            case R.id.nav_history:
                Intent it = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(it);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
