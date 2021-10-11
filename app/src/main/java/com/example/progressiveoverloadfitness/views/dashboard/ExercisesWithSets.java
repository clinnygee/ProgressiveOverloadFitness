package com.example.progressiveoverloadfitness.views.dashboard;

import android.util.Log;

import com.example.progressiveoverloadfitness.database.model.Exercise;
import com.example.progressiveoverloadfitness.database.model.Set;

import java.util.List;

public class ExercisesWithSets{
    Exercise exercise;
    List<Set> sets;
    ExercisesWithSets(Exercise exercise) {
        Log.d("Exercise", exercise.toString());
        this.exercise = exercise;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void addSets(List<Set> sets){
        this.sets = sets;
    }
}
