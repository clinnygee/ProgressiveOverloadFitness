package com.example.progressiveoverloadfitness.database.model;

import androidx.room.Entity;

@Entity(primaryKeys = {"exercise_id", "set_id"})
public class ExercisesWithSetsCrossRef {
    public int exerciseId;
    public int setId;
}
