package com.example.progressiveoverloadfitness.database.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "workout")
public class Workout {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "workout_id")
    public int id;

    @ColumnInfo(name = "completed")
    public boolean completed;

    @ColumnInfo(name = "start_time")
    public String startTime;

    @ColumnInfo(name = "end_time")
    public String endTime;

    public Workout(boolean completed, @NonNull String startTime, @NonNull String endTime){
        this.completed = completed;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
