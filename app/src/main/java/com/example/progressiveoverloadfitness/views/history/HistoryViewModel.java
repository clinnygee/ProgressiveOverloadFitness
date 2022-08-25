package com.example.progressiveoverloadfitness.views.history;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.progressiveoverloadfitness.database.POFRepository;
import com.example.progressiveoverloadfitness.database.model.WorkoutWithWorkoutExercisesAndSets;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class HistoryViewModel extends AndroidViewModel {
    private POFRepository mPOFRepository;

    private final List<WorkoutWithWorkoutExercisesAndSets> fullWorkouts;

    public HistoryViewModel(Application application){
        super(application);
        mPOFRepository = new POFRepository(application);
        fullWorkouts = mPOFRepository.getmAllWorkouts();
        Collections.sort(fullWorkouts, new Comparator<WorkoutWithWorkoutExercisesAndSets>() {
            @Override
            public int compare(WorkoutWithWorkoutExercisesAndSets o1, WorkoutWithWorkoutExercisesAndSets o2) {
                return new Date(o1.workout.startTime).compareTo(new Date(o1.workout.startTime));
            }
        });
    }

    public List<WorkoutWithWorkoutExercisesAndSets> getFullWorkouts() {
        return fullWorkouts;
    }
}
