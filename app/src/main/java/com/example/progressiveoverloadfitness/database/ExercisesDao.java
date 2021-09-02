package com.example.progressiveoverloadfitness.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExercisesDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Exercises exercise);

    @Query("DELETE FROM exercises")
    void deleteAll();

    @Query("SELECT * FROM exercises ORDER BY name ASC")
    LiveData<List<Exercises>> getAlphabetizedExercises();
}
