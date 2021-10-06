package com.example.progressiveoverloadfitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.example.progressiveoverloadfitness.database.POFViewModel;
import com.example.progressiveoverloadfitness.views.dashboard.DashboardActivity;
import com.example.progressiveoverloadfitness.views.exercises.ExerciseListAdaptor;

public class MainActivity extends AppCompatActivity {

    private POFViewModel mPOFViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        find the bottom navagation definition, and create a bottomnavigationview from it
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigatin_view);
//
//        NavController navController = Navigation.findNavController(this, R.id.nav_fragment);
//
//        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        Intent launchIntent = new Intent(MainActivity.this, DashboardActivity.class);
        startActivity(launchIntent);
        overridePendingTransition(0,0);

        ExerciseListAdaptor adapter = new ExerciseListAdaptor(new ExerciseListAdaptor.ExerciseDiff());
        mPOFViewModel = new ViewModelProvider(this).get(POFViewModel.class);

        mPOFViewModel.getAllExercises().observe(this, exercises -> {
            adapter.submitList(exercises);
        });
    }

}