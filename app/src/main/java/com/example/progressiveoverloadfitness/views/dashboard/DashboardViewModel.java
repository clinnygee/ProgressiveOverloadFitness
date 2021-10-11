package com.example.progressiveoverloadfitness.views.dashboard;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.progressiveoverloadfitness.database.POFRepository;
import com.example.progressiveoverloadfitness.database.model.Exercise;
import com.example.progressiveoverloadfitness.database.model.Set;

import java.util.ArrayList;
import java.util.List;

public class DashboardViewModel extends AndroidViewModel {
    ArrayList<ExercisesWithSets> exercises;
    LiveData<List<Exercise>> allExercises;
    LiveData<List<Set>> allSets;
    POFRepository mPOFRepository;

    public DashboardViewModel(Application application){
        super(application);
//        String bench = "Barbell Bench";
//        String squat = "Barbell Squat";
//        String deadlift = "Barbell Deadlift";
//        String ohp = "Barbell OHP";
//
//        exercises = new ArrayList<>();
        mPOFRepository = new POFRepository(application);
        allExercises = mPOFRepository.getAllExercises();
        allSets = mPOFRepository.getAllSets();
//
//        Exercise exercise= mPOFRepository.getExerciseByName(bench);
//        ExercisesWithSets exercisesWithSets = new ExercisesWithSets(exercise);

//        exercises.add(new ExercisesWithSets(mPOFRepository.getExerciseByName(bench)));
//        exercises.add(new ExercisesWithSets(mPOFRepository.getExerciseByName(squat)));
//        exercises.add(new ExercisesWithSets(mPOFRepository.getExerciseByName(deadlift)));
//        exercises.add(new ExercisesWithSets(mPOFRepository.getExerciseByName(ohp)));

//        for (ExercisesWithSets exercise : exercises) {
//            exercise.addSets(mPOFRepository.getSetsByExerciseId(exercise.getExercise().getId()));
//        }
    }


}
