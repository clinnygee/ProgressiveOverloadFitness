package com.example.progressiveoverloadfitness.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.progressiveoverloadfitness.DashboardFragment;
import com.example.progressiveoverloadfitness.ExercisesActivity;
import com.example.progressiveoverloadfitness.ExercisesFragment;
import com.example.progressiveoverloadfitness.HistoryActivity;
import com.example.progressiveoverloadfitness.R;
import com.example.progressiveoverloadfitness.views.workout.WorkoutActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        setTitle("Dashboard");

        //        find the bottom navagation definition, and create a bottomnavigationview from it
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigatin_view);

//        NavController navController = Navigation.findNavController(this, R.id.nav_dashboard);
//
//        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .setReorderingAllowed(true)
//                    .add(R.id.workout_fragment_container, ExercisesFragment.class, null)
//                    .commit();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            DashboardFragment dashboardFragment = new DashboardFragment();
            fragmentTransaction.replace(R.id.dashboard_fragment_container, dashboardFragment);
            fragmentTransaction.addToBackStack("dashboard");
            fragmentTransaction.commit();
        }

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dashboardActivity:
                        break;
                    case R.id.historyActivity:
                        Intent intent1 = new Intent(DashboardActivity.this, HistoryActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                    case R.id.workoutActivity:
                        Intent intent2 = new Intent(DashboardActivity.this, WorkoutActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.exercisesActivity:
                        Intent intent3 = new Intent(DashboardActivity.this, ExercisesActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0, 0);
                }
                return false;
            }
        });

    }
}