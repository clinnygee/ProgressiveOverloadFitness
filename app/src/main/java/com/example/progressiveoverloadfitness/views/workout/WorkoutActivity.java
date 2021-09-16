package com.example.progressiveoverloadfitness.views.workout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        NavController navController = Navigation.findNavController(this, R.id.nav_fragment);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.nav_fragment, WorkoutFragment.class, null)
                    .commit();
        }
//        ExerciseListAdaptor adapter = new ExerciseListAdaptor(new ExerciseListAdaptor.ExerciseDiff());
//        mPOFViewModel = new ViewModelProvider(this).get(POFViewModel.class);
//
//        mPOFViewModel.getAllExercises().observe(this, exercises -> {
//            adapter.submitList(exercises);
//        });

    }

    protected void startWorkout(){
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.nav_fragment, PerformWorkoutFragment.class,null)
                .commit();

        bottomNavigationView.setVisibility(View.INVISIBLE);

//        View container = findViewById(R.id.nav_fragment);
//
//        container.setVisibility(View.INVISIBLE);
    }

    protected void endWorkout(){
        Fragment workoutFragment =  getSupportFragmentManager().findFragmentById(R.id.workout_fragment_container);
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.nav_fragment, WorkoutFragment.class, null)
                .commit();

        bottomNavigationView.setVisibility(View.VISIBLE);

//        View container = findViewById(R.id.nav_fragment);
//
//        container.setVisibility(View.VISIBLE);
    }
}