package com.example.progressiveoverloadfitness.database.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ExerciseWithSets {
    @Embedded
    public Exercise exercise;
    @Relation(
            parentColumn = "exercise_id",
            entityColumn = "exercise_id"
    )
    public List<Set> sets;
}
