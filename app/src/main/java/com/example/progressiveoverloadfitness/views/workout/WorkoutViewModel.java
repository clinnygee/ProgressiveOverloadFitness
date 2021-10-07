package com.example.progressiveoverloadfitness.views.workout;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.progressiveoverloadfitness.database.POFRepository;
import com.example.progressiveoverloadfitness.database.model.Exercise;
import com.example.progressiveoverloadfitness.database.model.Workout;
import com.example.progressiveoverloadfitness.database.model.WorkoutExercise;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkoutViewModel extends AndroidViewModel {
    private POFRepository mPOFRepository;

    private final LiveData<List<Exercise>> mAllExercises;
    private Workout workout;
    public ArrayList<WorkoutExercise> workoutExercises;

    public WorkoutViewModel(Application application){
        super(application);
        mPOFRepository = new POFRepository(application);
        mAllExercises = mPOFRepository.getAllExercises();
        workoutExercises = new ArrayList<>();
    }

    public LiveData<List<Exercise>> getAllExercises(){
        return mAllExercises;
    }

    public void startWorkout(){
        Date startTime = new Date();
        this.workout = mPOFRepository.insertWorkout(new Workout(false, startTime.toString(), startTime.toString()));
        Log.d("workout_VM", this.workout.endTime);
    }

    public void addExercise(String name){
        Exercise newExercise = mPOFRepository.getExerciseByName(name);
        WorkoutExercise newWorkoutExercise = mPOFRepository.insertWorkoutExercise(new WorkoutExercise(newExercise.getId(), this.workout.id));
        workoutExercises.add(newWorkoutExercise);
        Log.d("exercise", String.valueOf(newWorkoutExercise.exerciseId));
    }

    public void addSet(int reps, int weight){

    }
}
