package com.example.progressiveoverloadfitness.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.progressiveoverloadfitness.database.model.WorkoutExercise;

import java.util.List;

@Dao
public interface WorkoutExerciseDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(WorkoutExercise workoutExercise);

    @Query("Select * FROM workout_exercise")
    LiveData<List<WorkoutExercise>> getAllWorkoutExercises();
}
