package com.example.progressiveoverloadfitness.views.workout;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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


    public Workout getWorkout() {
        return workout;
    }

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
//        this.workout = new Workout(false, startTime.toString(), startTime.toString());
        Workout newWorkout = new Workout(false, startTime.toString(), startTime.toString());
        mPOFRepository.insertWorkout(newWorkout);
        this.workout = mPOFRepository.findWorkoutByStartTime(startTime.toString());
//        Log.d("workout", this.workout.toString());

    }

    public void addExercise(String name){
        ArrayList<ExercisesWithSets> exercisesWithSetsArray = exercisesWithSets.getValue();
        Exercise newExercise = mPOFRepository.getExerciseByName(name);
//        Log.d("the value of this.work", this.workout.toString());
        if(this.workout == null){
            startWorkout();
            Log.d("the value of this.work", this.workout.toString());
        } else {

        }
        WorkoutExercise newWorkoutExercise = mPOFRepository.insertWorkoutExercise(new WorkoutExercise(newExercise.getId(), this.workout.id, name));
        workoutExercises.add(newWorkoutExercise);
        exercisesWithSetsArray.add(new ExercisesWithSets(newWorkoutExercise, name));
//        exercisesWithSets.setValue(exercisesWithSetsArray);
        exercisesWithSets.setValue(exercisesWithSetsArray);
        Log.d("exercise", String.valueOf(newWorkoutExercise.exerciseId));
//        addSet(name, 8, 80.0);
    }

    public void addSet(String exerciseName, int reps, double weight){
        Log.d("Set", exerciseName);
        ArrayList<ExercisesWithSets> exercisesWithSetsArray = exercisesWithSets.getValue();

        ExercisesWithSets chosenExercise = null;

        for (ExercisesWithSets exercise : exercisesWithSetsArray) {
            if(exercise.getName().equals(exerciseName)){
                Log.d("Set", "found the matching exercise");
                chosenExercise = exercise;
                break;
            }
        }
        if(chosenExercise != null){
            Log.d("Set", "it's not null");
            Set set = new Set(chosenExercise.getExercise().getId(), chosenExercise.getExercise().getExerciseId(), weight, reps, this.workout.startTime);
            Set addedSet = mPOFRepository.insertSet(set);
            chosenExercise.addSet(addedSet);
//            exercisesWithSets.setValue(exercisesWithSetsArray);
            ArrayList<ExercisesWithSets> temp = new ArrayList<>();
            exercisesWithSets.setValue(temp);
            exercisesWithSets.setValue(exercisesWithSetsArray);
        }


    }
}
