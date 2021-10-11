package com.example.progressiveoverloadfitness.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.progressiveoverloadfitness.database.model.Set;

import java.util.List;

@Dao
public interface SetDao {
    @Query("Select * FROM `set` WHERE exercise_id = :exerciseId")
    LiveData<List<Set>> getSetsByExerciseId(int exerciseId);

    @Query("Select * FROM `set`")
    LiveData<List<Set>> getAllSets();

    @Query("SELECT * FROM `set` WHERE exercise_id = :exerciseId")
    List<Set> getAllSetsByExercise(int exerciseId);

    @Query("SELECT * FROM `set` WHERE workout_exercise_id = :workoutExerciseId")
    Set getSetByWorkoutExercise(int workoutExerciseId);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertSet(Set set);
}
