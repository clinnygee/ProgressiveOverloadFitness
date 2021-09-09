package com.example.progressiveoverloadfitness.database.model;

import androidx.annotation.NonNull;
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
        ), @ForeignKey(
                entity = Exercise.class,
                parentColumns = "exercise_id",
                childColumns = "exercise_id"

        )}
)
public class Set {

        @PrimaryKey(autoGenerate = true)
        public int id;

        @ColumnInfo(name = "workout_exercise_id")
        public int workoutExerciseId;

        @ColumnInfo(name = "exercise_id")
        public int exercise_id;

        @ColumnInfo(name = "weight")
        public double weight;

        @ColumnInfo(name = "reps")
        public int reps;

        public Set(@NonNull int workoutExerciseId, @NonNull int exercise_id, @NonNull double weight, @NonNull int reps){
                this.exercise_id = exercise_id;
                this.workoutExerciseId = workoutExerciseId;
                this.weight = weight;
                this.reps = reps;
        }
}
