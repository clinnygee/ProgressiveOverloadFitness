package com.example.progressiveoverloadfitness.views.history;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.progressiveoverloadfitness.database.POFRepository;
import com.example.progressiveoverloadfitness.database.model.WorkoutWithWorkoutExercisesAndSets;

import java.util.List;

public class HistoryViewModel extends AndroidViewModel {
    private POFRepository mPOFRepository;

    private final List<WorkoutWithWorkoutExercisesAndSets> fullWorkouts;

    public HistoryViewModel(Application application){
        super(application);
        mPOFRepository = new POFRepository(application);
        fullWorkouts = mPOFRepository.getmAllWorkouts();
    }

    public List<WorkoutWithWorkoutExercisesAndSets> getFullWorkouts() {
        return fullWorkouts;
    }
}
