package com.example.progressiveoverloadfitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.progressiveoverloadfitness.database.POFViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private POFViewModel mPOFViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        find the bottom navagation definition, and create a bottomnavigationview from it
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigatin_view);

        NavController navController = Navigation.findNavController(this, R.id.nav_fragment);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        ExerciseListAdaptor adapter = new ExerciseListAdaptor(new ExerciseListAdaptor.ExerciseDiff());
        mPOFViewModel = new ViewModelProvider(this).get(POFViewModel.class);

        mPOFViewModel.getAllExercises().observe(this, exercises -> {
            adapter.submitList(exercises);
        });
    }

}