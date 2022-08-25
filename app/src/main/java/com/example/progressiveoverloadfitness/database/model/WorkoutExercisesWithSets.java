package com.example.progressiveoverloadfitness.database.model;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class WorkoutExercisesWithSets {
    @Embedded public WorkoutExercise workoutExercises;
    @Relation(
            entity = Set.class,
            parentColumn = "workout_exercise_id",
            entityColumn = "workout_exercise_id"
    )
    public List<Set> sets;
}
