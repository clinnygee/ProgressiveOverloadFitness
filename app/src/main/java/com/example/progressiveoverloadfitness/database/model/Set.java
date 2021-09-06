package com.example.progressiveoverloadfitness.database.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "set",
        foreignKeys = {@ForeignKey(
                entity = WorkoutExercise.class,
                parentColumns = "workout_exercise_id",
                childColumns = "workout_exercise_id"
        )}
)
public class Set {

        @PrimaryKey(autoGenerate = true)
        public int id;

        @ColumnInfo(name = "workout_exercise_id")
        public int workoutExerciseId;
}
