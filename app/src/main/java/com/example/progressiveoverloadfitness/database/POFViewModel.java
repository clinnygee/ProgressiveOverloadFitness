package com.example.progressiveoverloadfitness.database;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class POFViewModel extends AndroidViewModel {

    private POFRepository mPOFRepository;

    private final LiveData<List<Exercises>> mAllExercises;

    public POFViewModel(Application application){
        super(application);
        mPOFRepository = new POFRepository(application);
        mAllExercises = mPOFRepository.getAllExercises();
    }

    LiveData<List<Exercises>> getAllExercises(){
        return mAllExercises;
    }

    public void insert(Exercises exercises){
        mPOFRepository.insertExercise(exercises);
    }
}
