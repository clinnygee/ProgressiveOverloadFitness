package com.example.progressiveoverloadfitness.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;

import com.example.progressiveoverloadfitness.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        setTitle("Dashboard");

        //        find the bottom navagation definition, and create a bottomnavigationview from it
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigatin_view);

        NavController navController = Navigation.findNavController(this, R.id.nav_dashboard);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        Log.d("Dashboard", "running dashboard");
    }
}