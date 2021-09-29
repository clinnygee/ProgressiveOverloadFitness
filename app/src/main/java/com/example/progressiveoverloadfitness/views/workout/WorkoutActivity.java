package com.example.progressiveoverloadfitness.views.workout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.progressiveoverloadfitness.ExercisesActivity;
import com.example.progressiveoverloadfitness.HistoryActivity;
import com.example.progressiveoverloadfitness.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WorkoutActivity extends AppCompatActivity {
    Button startWorkout;
    WorkoutFragment workoutFragment;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        setTitle("Workout");

        //        find the bottom navagation definition, and create a bottomnavigationview from it
        bottomNavigationView = findViewById(R.id.bottom_navigatin_view);

//        NavController navController = Navigation.findNavController(this, R.id.nav_fragment);

//        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.workout_fragment_container, WorkoutFragment.class, null)
                    .commit();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            WorkoutFragment workoutFragment = new WorkoutFragment();
            fragmentTransaction.replace(R.id.workout_fragment_container, workoutFragment);
            fragmentTransaction.addToBackStack("workout");
            fragmentTransaction.commit();
        }

//        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
//        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
//        ExerciseListAdaptor adapter = new ExerciseListAdaptor(new ExerciseListAdaptor.ExerciseDiff());
//        mPOFViewModel = new ViewModelProvider(this).get(POFViewModel.class);
//
//        mPOFViewModel.getAllExercises().observe(this, exercises -> {
//            adapter.submitList(exercises);
//        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.historyActivity:
                        Intent intent1 = new Intent(WorkoutActivity.this, HistoryActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                    case R.id.workoutActivity:
                        break;
                    case R.id.exercisesActivity:
                        Intent intent3 = new Intent(WorkoutActivity.this, ExercisesActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0,0);
                }
                return false;
            }
        });

    }

    protected void startWorkout(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PerformWorkoutFragment performWorkoutFragment = new PerformWorkoutFragment();
        fragmentTransaction.replace(R.id.workout_fragment_container, performWorkoutFragment);
        fragmentTransaction.addToBackStack("perform_workout");
        fragmentTransaction.commit();
//        getSupportFragmentManager().beginTransaction()
//                .setReorderingAllowed(true)
//                .replace(R.id.nav_fragment, PerformWorkoutFragment.class,null)
//                .commit();

        bottomNavigationView.setVisibility(View.INVISIBLE);

//        View container = findViewById(R.id.nav_fragment);
//
//        container.setVisibility(View.INVISIBLE);
    }

    protected void endWorkout(){
        Fragment workoutFragment =  getSupportFragmentManager().findFragmentById(R.id.workout_fragment_container);
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.workout_fragment_container, WorkoutFragment.class, null)
                .commit();

        bottomNavigationView.setVisibility(View.VISIBLE);

//        View container = findViewById(R.id.nav_fragment);
//
//        container.setVisibility(View.VISIBLE);
    }
}