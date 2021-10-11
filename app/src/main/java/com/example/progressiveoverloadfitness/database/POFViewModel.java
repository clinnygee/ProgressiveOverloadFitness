package com.example.progressiveoverloadfitness.database;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.progressiveoverloadfitness.database.model.Exercise;
import com.example.progressiveoverloadfitness.database.model.Workout;
import com.example.progressiveoverloadfitness.database.model.WorkoutWithWorkoutExercisesAndSets;

import java.util.List;

public class POFViewModel extends AndroidViewModel {

    private POFRepository mPOFRepository;

    private final LiveData<List<Exercise>> mAllExercises;
    private final List<WorkoutWithWorkoutExercisesAndSets> mAllWorkouts;

    public POFViewModel(Application application){
        super(application);
        mPOFRepository = new POFRepository(application);
        mAllExercises = mPOFRepository.getAllExercises();
        mAllWorkouts = mPOFRepository.getAllWorkouts();

    }

    public LiveData<List<Exercise>> getAllExercises(){
        return mAllExercises;
    }

    public List<WorkoutWithWorkoutExercisesAndSets> getAllWorkouts() {
        return mAllWorkouts;
    }

    public void insert(Exercise exercises){
        mPOFRepository.insertExercise(exercises);
    }
}
