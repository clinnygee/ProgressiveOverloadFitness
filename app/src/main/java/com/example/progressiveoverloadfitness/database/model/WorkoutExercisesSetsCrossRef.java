package com.example.progressiveoverloadfitness.database.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import java.util.List;
@Entity(primaryKeys = {"workout_exercise_id", "set_id"})
public class WorkoutExercisesSetsCrossRef {
    public int workout_exercise_id;
    public int set_id;
}
