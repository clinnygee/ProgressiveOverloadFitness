package com.example.progressiveoverloadfitness.database.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "dashboard_item",
        foreignKeys = {@ForeignKey(
                entity = Exercise.class,
                parentColumns = "exercise_id",
                childColumns = "exercise_id"
        )}
)
public class DashboardItem {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "dashboard_item_id")
    public int id;
    @ColumnInfo(name = "exercise_id")
    public int exerciseId;

    public DashboardItem(@NonNull int exerciseId){
        this.exerciseId = exerciseId;
    }
}
