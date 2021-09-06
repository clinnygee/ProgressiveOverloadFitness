package com.example.progressiveoverloadfitness.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.progressiveoverloadfitness.database.dao.ExercisesDao;
import com.example.progressiveoverloadfitness.database.model.Exercise;

import java.util.List;

public class POFRepository {

    private ExercisesDao mExercisesDao;
    private LiveData<List<Exercise>> mAllExercises;

    POFRepository(Application application){
        POFRoomDatabase db = POFRoomDatabase.getDatabase(application);
        mExercisesDao = db.exercisesDao();
        mAllExercises = mExercisesDao.getAlphabetizedExercises();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Exercise>> getAllExercises(){
        return mAllExercises;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insertExercise(Exercise exercise){
        POFRoomDatabase.databaseWriteExecutor.execute(() -> {
            mExercisesDao.insert(exercise);
        });
    }
}
