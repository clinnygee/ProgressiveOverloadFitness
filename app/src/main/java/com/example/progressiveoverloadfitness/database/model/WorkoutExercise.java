package com.example.progressiveoverloadfitness.database.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "workout_exercise",
        foreignKeys = {@ForeignKey(
                entity = Workout.class,
                parentColumns = "workout_id",
                childColumns = "workout_id"
        ), @ForeignKey(
                entity = Exercise.class,
                parentColumns = "exercise_id",
                childColumns = "exercise_id"

        )}
)
public class WorkoutExercise {

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "workout_exercise_id")
        public int id;

        @ColumnInfo(name= "exercise_id")
        public int exerciseId;

        @ColumnInfo(name = "workout_id")
        public int workoutId;

        public WorkoutExercise(@NonNull int exerciseId, @NonNull int workoutId){
                this.exerciseId = exerciseId;
                this.workoutId = workoutId;
        }

        public int getId(){
                return this.id;
        }

        public int getExerciseId() {
                return exerciseId;
        }
}
