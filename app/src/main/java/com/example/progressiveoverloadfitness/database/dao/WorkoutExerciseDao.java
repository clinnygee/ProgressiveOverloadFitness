package com.example.progressiveoverloadfitness.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.progressiveoverloadfitness.database.model.WorkoutExercise;

import java.util.List;

@Dao
public interface WorkoutExerciseDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(WorkoutExercise workoutExercise);

    @Transaction
    @Query("Select * FROM workout_exercise")
    List<WorkoutExercise> getAllWorkoutExercises();

    @Query("SELECT workout_exercise_id FROM workout_exercise WHERE workout_id = :workoutId AND exercise_id = :exerciseId")
    int findIdByExerciseAndWorkoutId(int workoutId, int exerciseId);

    @Query("SELECT * FROM workout_exercise WHERE workout_id = :workoutId AND exercise_id = :exerciseId")
    WorkoutExercise findByExerciseAndWorkoutId(int workoutId, int exerciseId);
}
