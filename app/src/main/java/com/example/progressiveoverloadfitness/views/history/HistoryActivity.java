package com.example.progressiveoverloadfitness.views.history;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.progressiveoverloadfitness.R;
import com.example.progressiveoverloadfitness.views.dashboard.DashboardActivity;
import com.example.progressiveoverloadfitness.views.exercises.ExercisesActivity;
import com.example.progressiveoverloadfitness.views.workout.WorkoutActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HistoryActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        setTitle("History");

        //        find the bottom navagation definition, and create a bottomnavigationview from it
        bottomNavigationView = findViewById(R.id.bottom_navigatin_view);

//        NavController navController = Navigation.findNavController(this, R.id.nav_fragment);

//        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .setReorderingAllowed(true)
//                    .add(R.id.workout_fragment_container, ExercisesFragment.class, null)
//                    .commit();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            HistoryFragment historyFragment = new HistoryFragment();
            fragmentTransaction.replace(R.id.history_fragment_container, historyFragment);
            fragmentTransaction.addToBackStack("history");
            fragmentTransaction.commit();
        }

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d("Menu Clicked", String.valueOf(item.getItemId()));
                switch (item.getItemId()){
                    case R.id.dashboardActivity:
                        Intent intent0 = new Intent(HistoryActivity.this, DashboardActivity.class);
                        startActivity(intent0);
                        overridePendingTransition(0,0);
                    case R.id.historyActivity:
                        break;
                    case R.id.workoutActivity:
                        Intent intent2 = new Intent(HistoryActivity.this, WorkoutActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.exercisesActivity:
                        Intent intent3 = new Intent(HistoryActivity.this, ExercisesActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0,0);
                        break;
                }
                return false;
            }
        });
    }
}