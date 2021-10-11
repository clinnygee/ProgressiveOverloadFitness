package com.example.progressiveoverloadfitness.database.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class WorkoutWithWorkoutExercisesAndSets {
    @Embedded public Workout workout;
    @Relation(
            entity = WorkoutExercise.class,
            parentColumn = "workout_id",
            entityColumn = "workout_id"
    )
    public List<WorkoutExercisesWithSets> exercisesWithSets;

    public int getWorkoutId(){
        return this.workout.id;
    }
}
