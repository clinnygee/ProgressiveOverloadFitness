package com.example.progressiveoverloadfitness.database.model;

import androidx.room.Embedded;
import androidx.room.Relation;



import java.util.List;

public class DashboardItemWithExercisesAndSets {
    @Embedded
    public DashboardItem dashboardItem;
    @Relation(
            entity = Exercise.class,
            parentColumn = "exercise_id",
            entityColumn = "exercise_id"
    )
    public ExerciseWithSets exerciseWithSets;

    public int getDashboardItemId(){
        return this.dashboardItem.id;
    }
}
