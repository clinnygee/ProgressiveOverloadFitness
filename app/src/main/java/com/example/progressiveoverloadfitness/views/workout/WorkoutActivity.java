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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        setTitle("Workout");

        //        find the bottom navagation definition, and create a bottomnavigationview from it
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigatin_view);

        NavController navController = Navigation.findNavController(this, R.id.nav_fragment);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

//        ExerciseListAdaptor adapter = new ExerciseListAdaptor(new ExerciseListAdaptor.ExerciseDiff());
//        mPOFViewModel = new ViewModelProvider(this).get(POFViewModel.class);
//
//        mPOFViewModel.getAllExercises().observe(this, exercises -> {
//            adapter.submitList(exercises);
//        });
        startWorkout = findViewById(R.id.startWorkout);

        startWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWorkout(v);
            }
        });
    }

    protected void startWorkout(View v){
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.workout_fragment_container, WorkoutFragment.class,null)
                .commit();
    }
}