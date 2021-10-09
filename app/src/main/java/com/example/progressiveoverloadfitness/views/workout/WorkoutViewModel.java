package com.example.progressiveoverloadfitness.views.workout;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.progressiveoverloadfitness.database.POFRepository;
import com.example.progressiveoverloadfitness.database.model.Exercise;
import com.example.progressiveoverloadfitness.database.model.Set;
import com.example.progressiveoverloadfitness.database.model.Workout;
import com.example.progressiveoverloadfitness.database.model.WorkoutExercise;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkoutViewModel extends AndroidViewModel {
    private POFRepository mPOFRepository;
//    private static WorkoutViewModel instance;

    private final LiveData<List<Exercise>> mAllExercises;
    private Workout workout;
    public ArrayList<WorkoutExercise> workoutExercises;
    private MutableLiveData<ArrayList<ExercisesWithSets>> exercisesWithSets;

    public WorkoutViewModel(Application application){
        super(application);
        mPOFRepository = new POFRepository(application);
        mAllExercises = mPOFRepository.getAllExercises();
        workoutExercises = new ArrayList<>();
    }

//    public static WorkoutViewModel getInstance() {
//        if(instance != null){
//            return instance;
//        } else {
//            new ViewModelProvider(this).get(WorkoutViewModel.class)
//        }
//        return instance;
//    }

    public LiveData<List<Exercise>> getAllExercises(){
        return mAllExercises;
    }

    public MutableLiveData<ArrayList<ExercisesWithSets>> getExercisesWithSets(){
        if(exercisesWithSets == null){
            Log.d("constructor", "we in here");
            exercisesWithSets = new MutableLiveData<>();
            ArrayList<ExercisesWithSets> exercisesWithSetsArray = new ArrayList();
//            exercisesWithSetsArray.add(new )
            exercisesWithSets.setValue(exercisesWithSetsArray);
        }
        return exercisesWithSets;
    }

    public void startWorkout(){
        Date startTime = new Date();
        this.workout = mPOFRepository.insertWorkout(new Workout(false, startTime.toString(), startTime.toString()));
        Log.d("workout", this.workout.toString());

    }

    public void addExercise(String name){
        ArrayList<ExercisesWithSets> exercisesWithSetsArray = exercisesWithSets.getValue();
        Exercise newExercise = mPOFRepository.getExerciseByName(name);
        if(this.workout == null){
            startWorkout();
        }
        WorkoutExercise newWorkoutExercise = mPOFRepository.insertWorkoutExercise(new WorkoutExercise(newExercise.getId(), this.workout.id));
        workoutExercises.add(newWorkoutExercise);
        exercisesWithSetsArray.add(new ExercisesWithSets(newWorkoutExercise, name));
        exercisesWithSets.setValue(exercisesWithSetsArray);
        Log.d("exercise", String.valueOf(newWorkoutExercise.exerciseId));
    }

    public void addSet(String exerciseName, int reps, double weight){
        Log.d("Set", exerciseName);
        ArrayList<ExercisesWithSets> exercisesWithSetsArray = exercisesWithSets.getValue();

        ExercisesWithSets chosenExercise = null;

        for (ExercisesWithSets exercise : exercisesWithSetsArray) {
            if(exercise.getName().equals(exerciseName)){
                chosenExercise = exercise;
                break;
            }
        }
        if(chosenExercise != null){
            Set set = new Set(chosenExercise.getExercise().getId(), chosenExercise.getExercise().getExerciseId(), weight, reps);
            Set addedSet = mPOFRepository.insertSet(set);
            chosenExercise.addSet(addedSet);
            exercisesWithSets.setValue(exercisesWithSetsArray);
        }


    }
}
