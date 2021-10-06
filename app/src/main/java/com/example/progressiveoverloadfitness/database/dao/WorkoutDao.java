package com.example.progressiveoverloadfitness.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.progressiveoverloadfitness.database.model.Workout;
import com.example.progressiveoverloadfitness.database.model.WorkoutWithWorkoutExercisesAndSets;

import java.util.List;

@Dao
public interface WorkoutDao {
//    @Query("Select * FROM workout")
//    LiveData<List<Workout>> getAllWorkouts();

    @Insert
    void insertWorkout(Workout workout);

    @Query("SELECT workout_id FROM workout WHERE start_time = :startTime")
    int findByStartTime(String startTime);

    @Transaction
    @Query("SELECT * FROM workout")
    public LiveData<List<WorkoutWithWorkoutExercisesAndSets>> getAllWorkoutInformation();
}
