package com.example.progressiveoverloadfitness.database.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class WorkoutExercisesWithSets {
    @Embedded public WorkoutExercise workoutExercises;
    @Relation(
            parentColumn = "workout_exercise_id",
            entityColumn = "set_id"
    )
    public List<Set> sets;
}
