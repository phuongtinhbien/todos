package com.example.ui.todos.domains.mainEnglish;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ui.todos.R;
import com.example.ui.todos.domains.codeTest.CodeTestActivity_;
import com.example.ui.todos.domains.listen.ListenActivity_;
import com.example.ui.todos.domains.word.WordActivity_;
import com.example.ui.todos.domains.write.WriteActivity_;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainEnglishActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    LinearLayout llWord;
    LinearLayout llListen;
    LinearLayout llWrite;
    LinearLayout llTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_english);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("English-IT");
        FloatingActionButton fab = findViewById(R.id.fab);
        llWord = findViewById(R.id.llWord);
        llListen = findViewById(R.id.llListen);
        llWrite = findViewById(R.id.llWrite);
        llTest = findViewById(R.id.llTest);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        llWord.setOnClickListener(v -> {
            startActivity(new Intent(MainEnglishActivity.this, WordActivity_.class));
        });
        llWrite.setOnClickListener(v -> {
            startActivity(new Intent(MainEnglishActivity.this, WriteActivity_.class));
        });
        llListen.setOnClickListener(v->{
            startActivity(new Intent(MainEnglishActivity.this, ListenActivity_.class));
        });

        llTest.setOnClickListener(v->{
            startActivity(new Intent(MainEnglishActivity.this, CodeTestActivity_.class));
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
