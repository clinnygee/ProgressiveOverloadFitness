package com.example.progressiveoverloadfitness.views.workout;
import com.example.progressiveoverloadfitness.database.model.Set;
import com.example.progressiveoverloadfitness.database.model.WorkoutExercise;

import java.util.ArrayList;

public class ExercisesWithSets {
    private String name;
    private WorkoutExercise exercise;
    private ArrayList sets;

    public ExercisesWithSets(WorkoutExercise exercise, String name){
        this.name = name;
        this.exercise = exercise;
        this.sets = new ArrayList<Set>();
    }

    public void addSet(Set set){
        this.sets.add(set);
    }

    public String getName() {
        return name;
    }

    public ArrayList getSets() {
        return sets;
    }

    public WorkoutExercise getExercise() {
        return exercise;
    }
}
