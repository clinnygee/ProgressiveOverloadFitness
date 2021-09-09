package com.example.progressiveoverloadfitness.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.progressiveoverloadfitness.database.model.Workout;

import java.util.List;

@Dao
public interface WorkoutDao {
    @Query("Select * FROM workout")
    LiveData<List<Workout>> getAllWorkouts();

    @Insert
    void insertWorkout(Workout workout);
}
