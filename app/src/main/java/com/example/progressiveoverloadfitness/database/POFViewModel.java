package com.example.progressiveoverloadfitness.database;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.progressiveoverloadfitness.database.model.Exercise;

import java.util.List;

public class POFViewModel extends AndroidViewModel {

    private POFRepository mPOFRepository;

    private final LiveData<List<Exercise>> mAllExercises;

    public POFViewModel(Application application){
        super(application);
        mPOFRepository = new POFRepository(application);
        mAllExercises = mPOFRepository.getAllExercises();
    }

    public LiveData<List<Exercise>> getAllExercises(){
        return mAllExercises;
    }

    public void insert(Exercise exercises){
        mPOFRepository.insertExercise(exercises);
    }
}
