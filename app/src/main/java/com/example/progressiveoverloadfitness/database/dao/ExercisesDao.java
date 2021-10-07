package com.example.progressiveoverloadfitness.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.progressiveoverloadfitness.database.model.Exercise;

import java.util.List;

@Dao
public interface ExercisesDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Exercise exercise);

    @Query("DELETE FROM Exercise")
    void deleteAll();

    @Query("SELECT * FROM Exercise ORDER BY name ASC")
    LiveData<List<Exercise>> getAlphabetizedExercises();

    @Query("SELECT exercise_id FROM Exercise WHERE name = :exerciseName")
    int getIdByName(String exerciseName);

    @Query("SELECT * FROM Exercise WHERE name = :exerciseName")
    Exercise getByName(String exerciseName);
}
