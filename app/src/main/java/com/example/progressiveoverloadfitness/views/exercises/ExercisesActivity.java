package com.example.progressiveoverloadfitness.views.exercises;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.progressiveoverloadfitness.views.history.HistoryActivity;
import com.example.progressiveoverloadfitness.R;
import com.example.progressiveoverloadfitness.views.dashboard.DashboardActivity;
import com.example.progressiveoverloadfitness.views.workout.WorkoutActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ExercisesActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        setTitle("Exercises");

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
            ExercisesFragment exercisesFragment = new ExercisesFragment();
            fragmentTransaction.replace(R.id.exercises_fragment_container, exercisesFragment);
            fragmentTransaction.addToBackStack("exercises");
            fragmentTransaction.commit();
        }

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d("Menu Clicked", String.valueOf(item.getItemId()));
                switch (item.getItemId()){
                    case R.id.dashboardActivity:
                        Intent intent0 = new Intent(ExercisesActivity.this, DashboardActivity.class);
                        startActivity(intent0);
                    case R.id.historyActivity:
                        Intent intent1 = new Intent(ExercisesActivity.this, HistoryActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                    case R.id.workoutActivity:
                        Intent intent2 = new Intent(ExercisesActivity.this, WorkoutActivity.class);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        break;
                    case R.id.exercisesActivity:
                        break;
                }
                return false;
            }
        });
    }
}