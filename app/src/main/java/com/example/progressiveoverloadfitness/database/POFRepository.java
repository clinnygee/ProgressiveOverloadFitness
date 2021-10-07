package com.example.progressiveoverloadfitness.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.progressiveoverloadfitness.database.dao.ExercisesDao;
import com.example.progressiveoverloadfitness.database.dao.SetDao;
import com.example.progressiveoverloadfitness.database.dao.WorkoutDao;
import com.example.progressiveoverloadfitness.database.dao.WorkoutExerciseDao;
import com.example.progressiveoverloadfitness.database.model.Exercise;
import com.example.progressiveoverloadfitness.database.model.Set;
import com.example.progressiveoverloadfitness.database.model.Workout;
import com.example.progressiveoverloadfitness.database.model.WorkoutExercise;
import com.example.progressiveoverloadfitness.database.model.WorkoutWithWorkoutExercisesAndSets;

import java.util.List;

public class POFRepository {

    private ExercisesDao mExercisesDao;
    private LiveData<List<Exercise>> mAllExercises;

    private WorkoutDao mWorkoutDao;
    private LiveData<List<WorkoutWithWorkoutExercisesAndSets>> mAllWorkouts;

    private WorkoutExerciseDao mWorkoutExerciseDao;
    private LiveData<List<WorkoutExercise>> mAllWorkoutExercise;

    private SetDao mSetDao;
    private LiveData<List<Set>> mAllSets;

    public POFRepository(Application application){
        POFRoomDatabase db = POFRoomDatabase.getDatabase(application);
        mExercisesDao = db.exercisesDao();
        mAllExercises = mExercisesDao.getAlphabetizedExercises();

        mWorkoutDao = db.workoutDao();
        mAllWorkouts = mWorkoutDao.getAllWorkoutInformation();

        mWorkoutExerciseDao = db.workoutExerciseDao();
        mAllWorkoutExercise = mWorkoutExerciseDao.getAllWorkoutExercises();

        mSetDao = db.setDao();
        mAllSets = mSetDao.getAllSets();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Exercise>> getAllExercises(){
        return mAllExercises;
    }

    LiveData<List<WorkoutWithWorkoutExercisesAndSets>> getAllWorkouts(){ return mAllWorkouts;}

    LiveData<List<WorkoutExercise>> getAllWorkoutExercises(){
        return mAllWorkoutExercise;
    }

    LiveData<List<Set>> getAllSets(){return mAllSets;}

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insertExercise(Exercise exercise){
        POFRoomDatabase.databaseWriteExecutor.execute(() -> {
            mExercisesDao.insert(exercise);
        });
    }

    public Workout insertWorkout(Workout workout){
        POFRoomDatabase.databaseWriteExecutor.execute(() -> {
            mWorkoutDao.insertWorkout(workout);
        });
        return mWorkoutDao.findWorkoutByStartTime(workout.startTime);
    }

    public Exercise getExerciseByName(String name){
        return mExercisesDao.getByName(name);
    }

    public WorkoutExercise insertWorkoutExercise(WorkoutExercise workoutExercise){
        mWorkoutExerciseDao.insert(workoutExercise);

        return mWorkoutExerciseDao.findByExerciseAndWorkoutId(workoutExercise.workoutId, workoutExercise.exerciseId);
    }

    public Set insertSet(Set set){
        mSetDao.insertSet(set);

        return mSetDao.getSetByWorkoutExercise(set.workoutExerciseId);
    }
}
