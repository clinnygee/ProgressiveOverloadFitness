package com.example.progressiveoverloadfitness.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.progressiveoverloadfitness.database.dao.DashboardItemDao;
import com.example.progressiveoverloadfitness.database.dao.ExercisesDao;
import com.example.progressiveoverloadfitness.database.dao.SetDao;
import com.example.progressiveoverloadfitness.database.dao.WorkoutDao;
import com.example.progressiveoverloadfitness.database.dao.WorkoutExerciseDao;
import com.example.progressiveoverloadfitness.database.model.DashboardItem;
import com.example.progressiveoverloadfitness.database.model.Exercise;
import com.example.progressiveoverloadfitness.database.model.Set;
import com.example.progressiveoverloadfitness.database.model.Workout;
import com.example.progressiveoverloadfitness.database.model.WorkoutExercise;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {
        Exercise.class,
        Workout.class,
        WorkoutExercise.class,
        Set.class,
        DashboardItem.class,
        },
        version = 4,
        exportSchema = false)
public abstract class POFRoomDatabase extends RoomDatabase {

    public abstract ExercisesDao exercisesDao();
    public abstract WorkoutDao workoutDao();
    public abstract WorkoutExerciseDao workoutExerciseDao();
    public abstract SetDao setDao();
    public abstract DashboardItemDao dashboardItemDao();

    private static volatile POFRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static POFRoomDatabase getDatabase(final Context context){
        context.deleteDatabase("pof_database");
        if(INSTANCE == null){
            synchronized (POFRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            POFRoomDatabase.class, "pof_database").fallbackToDestructiveMigration().addCallback(sRoomDatabaseSeeder).allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseSeeder = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            databaseWriteExecutor.execute(() ->{
//                Populate the database in the background
                ExercisesDao exercisesDao = INSTANCE.exercisesDao();
                exercisesDao.deleteAll();

                WorkoutDao workoutDao = INSTANCE.workoutDao();
                WorkoutExerciseDao workoutExerciseDao = INSTANCE.workoutExerciseDao();
                SetDao setDao = INSTANCE.setDao();
                DashboardItemDao dashboardItemDao = INSTANCE.dashboardItemDao();

                Date workoutTime = new Date();
                Date workoutEndTime = new Date(System.currentTimeMillis() + 3600000);

                Workout workout = new Workout(true, workoutTime.toString(), workoutEndTime.toString());
                workoutDao.insertWorkout(workout);
                Exercise exercise = new Exercise("Barbell Bench Press", "", "Chest");
                exercisesDao.insert(exercise);

                int workoutId = workoutDao.findByStartTime(workoutTime.toString());
                int exerciseId = exercisesDao.getIdByName("Barbell Bench Press");
                DashboardItem dashboardItem = new DashboardItem(exerciseId);
                dashboardItemDao.insert(dashboardItem);

                WorkoutExercise workoutExercise = new WorkoutExercise(exerciseId, workoutId, exercise.getName());
                workoutExerciseDao.insert(workoutExercise);

                int workoutExerciseId = workoutExerciseDao.findIdByExerciseAndWorkoutId(workoutId, exerciseId);
                Set firstSet = new Set(workoutExerciseId, exerciseId, 70, 8, workoutTime.toString());
                setDao.insertSet(firstSet);
                Set secondSet = new Set(workoutExerciseId, exerciseId, 80, 5, workoutTime.toString());
                setDao.insertSet(secondSet);
                Set thirdSet = new Set(workoutExerciseId, exerciseId, 90, 5, workoutTime.toString());
                setDao.insertSet(thirdSet);

//                Second Exercise

                exercise = new Exercise("Barbell Squat", "", "Legs");
                exercisesDao.insert(exercise);

//                exerciseId = exercisesDao.getIdByName("Barbell Squat");
                exerciseId = exercisesDao.getIdByName("Barbell Squat");
                dashboardItem = new DashboardItem(exerciseId);
                dashboardItemDao.insert(dashboardItem);

                workoutExercise = new WorkoutExercise(exerciseId, workoutId, exercise.getName());
                workoutExerciseDao.insert(workoutExercise);

                workoutExerciseId = workoutExerciseDao.findIdByExerciseAndWorkoutId(workoutId, exerciseId);

                firstSet = new Set(workoutExerciseId, exerciseId, 100, 8, workoutTime.toString());
                setDao.insertSet(firstSet);
                secondSet = new Set(workoutExerciseId, exerciseId, 110, 5, workoutTime.toString());
                setDao.insertSet(secondSet);
                thirdSet = new Set(workoutExerciseId, exerciseId, 120, 5, workoutTime.toString());
                setDao.insertSet(thirdSet);

//                Third Exercise
                exercise = new Exercise("Barbell Deadlift", "", "Legs");
                exercisesDao.insert(exercise);

                exerciseId = exercisesDao.getIdByName("Barbell Deadlift");
                workoutExercise = new WorkoutExercise(exerciseId,workoutId, exercise.getName());
                workoutExerciseDao.insert(workoutExercise);
                dashboardItem = new DashboardItem(exerciseId);
                dashboardItemDao.insert(dashboardItem);

                workoutExerciseId = workoutExerciseDao.findIdByExerciseAndWorkoutId(workoutId, exerciseId);

                firstSet = new Set(workoutExerciseId, exerciseId, 120, 8, workoutTime.toString());
                setDao.insertSet(firstSet);
                secondSet = new Set(workoutExerciseId, exerciseId, 130, 5, workoutTime.toString());
                setDao.insertSet(secondSet);
                thirdSet = new Set(workoutExerciseId, exerciseId, 145, 5, workoutTime.toString());
                setDao.insertSet(thirdSet);


                exercise = new Exercise("Barbell OHP", "", "Shoulders");
                exercisesDao.insert(exercise);

                exerciseId = exercisesDao.getIdByName("Barbell OHP");
                workoutExercise = new WorkoutExercise( exerciseId,workoutId, exercise.getName());
                workoutExerciseDao.insert(workoutExercise);
                dashboardItem = new DashboardItem(exerciseId);
                dashboardItemDao.insert(dashboardItem);

                workoutExerciseId = workoutExerciseDao.findIdByExerciseAndWorkoutId(workoutId, exerciseId);

                firstSet = new Set(workoutExerciseId, exerciseId, 40, 8, workoutTime.toString());
                setDao.insertSet(firstSet);
                secondSet = new Set(workoutExerciseId, exerciseId, 50, 5, workoutTime.toString());
                setDao.insertSet(secondSet);
                thirdSet = new Set(workoutExerciseId, exerciseId, 55, 5, workoutTime.toString());
                setDao.insertSet(thirdSet);

//                Second workout, on a different day 3 days before now

                workoutTime = new Date(System.currentTimeMillis() - 259200000);
                workoutEndTime = new Date(System.currentTimeMillis() - 259200000 + 3600000);

                workout = new Workout(true, workoutTime.toString(), workoutEndTime.toString());
                workoutDao.insertWorkout(workout);

                workoutId = workoutDao.findByStartTime(workoutTime.toString());
                exerciseId = exercisesDao.getIdByName("Barbell Bench Press");


                workoutExercise = new WorkoutExercise(exerciseId, workoutId, exercise.getName());
                workoutExerciseDao.insert(workoutExercise);

                workoutExerciseId = workoutExerciseDao.findIdByExerciseAndWorkoutId(workoutId, exerciseId);
                firstSet = new Set(workoutExerciseId, exerciseId, 72, 8, workoutTime.toString());
                setDao.insertSet(firstSet);
                secondSet = new Set(workoutExerciseId, exerciseId, 82, 5, workoutTime.toString());
                setDao.insertSet(secondSet);
                thirdSet = new Set(workoutExerciseId, exerciseId, 92, 5, workoutTime.toString());
                setDao.insertSet(thirdSet);

//                Second Exercise

                exerciseId = exercisesDao.getIdByName("Barbell Squat");

                workoutExercise = new WorkoutExercise(exerciseId, workoutId, exercise.getName());
                workoutExerciseDao.insert(workoutExercise);

                workoutExerciseId = workoutExerciseDao.findIdByExerciseAndWorkoutId(workoutId, exerciseId);

                firstSet = new Set(workoutExerciseId, exerciseId, 102, 8, workoutTime.toString());
                setDao.insertSet(firstSet);
                secondSet = new Set(workoutExerciseId, exerciseId, 112, 5, workoutTime.toString());
                setDao.insertSet(secondSet);
                thirdSet = new Set(workoutExerciseId, exerciseId, 125, 5, workoutTime.toString());
                setDao.insertSet(thirdSet);

//                Third Exercise

                exerciseId = exercisesDao.getIdByName("Barbell Deadlift");
                workoutExercise = new WorkoutExercise(exerciseId,workoutId, exercise.getName());
                workoutExerciseDao.insert(workoutExercise);

                workoutExerciseId = workoutExerciseDao.findIdByExerciseAndWorkoutId(workoutId, exerciseId);

                firstSet = new Set(workoutExerciseId, exerciseId, 125, 8, workoutTime.toString());
                setDao.insertSet(firstSet);
                secondSet = new Set(workoutExerciseId, exerciseId, 135, 5, workoutTime.toString());
                setDao.insertSet(secondSet);
                thirdSet = new Set(workoutExerciseId, exerciseId, 150, 5, workoutTime.toString());
                setDao.insertSet(thirdSet);

//                Fourth Exercise

                exerciseId = exercisesDao.getIdByName("Barbell OHP");
                workoutExercise = new WorkoutExercise( exerciseId,workoutId, exercise.getName());
                workoutExerciseDao.insert(workoutExercise);

                workoutExerciseId = workoutExerciseDao.findIdByExerciseAndWorkoutId(workoutId, exerciseId);

                firstSet = new Set(workoutExerciseId, exerciseId, 45, 8, workoutTime.toString());
                setDao.insertSet(firstSet);
                secondSet = new Set(workoutExerciseId, exerciseId, 55, 5, workoutTime.toString());
                setDao.insertSet(secondSet);
                thirdSet = new Set(workoutExerciseId, exerciseId, 60, 5, workoutTime.toString());
                setDao.insertSet(thirdSet);

//                third workout

                workoutTime = new Date(System.currentTimeMillis() - 259200000 * 2);
                workoutEndTime = new Date(System.currentTimeMillis() - 259200000 * 2 + 3600000);

                workout = new Workout(true, workoutTime.toString(), workoutEndTime.toString());
                workoutDao.insertWorkout(workout);

                workoutId = workoutDao.findByStartTime(workoutTime.toString());
                exerciseId = exercisesDao.getIdByName("Barbell Bench Press");


                workoutExercise = new WorkoutExercise(exerciseId, workoutId, exercise.getName());
                workoutExerciseDao.insert(workoutExercise);

                workoutExerciseId = workoutExerciseDao.findIdByExerciseAndWorkoutId(workoutId, exerciseId);
                firstSet = new Set(workoutExerciseId, exerciseId, 75, 8, workoutTime.toString());
                setDao.insertSet(firstSet);
                secondSet = new Set(workoutExerciseId, exerciseId, 85, 5, workoutTime.toString());
                setDao.insertSet(secondSet);
                thirdSet = new Set(workoutExerciseId, exerciseId, 93, 5, workoutTime.toString());
                setDao.insertSet(thirdSet);

//                Second Exercise

                exerciseId = exercisesDao.getIdByName("Barbell Squat");

                workoutExercise = new WorkoutExercise(exerciseId, workoutId, exercise.getName());
                workoutExerciseDao.insert(workoutExercise);

                workoutExerciseId = workoutExerciseDao.findIdByExerciseAndWorkoutId(workoutId, exerciseId);

                firstSet = new Set(workoutExerciseId, exerciseId, 104, 8, workoutTime.toString());
                setDao.insertSet(firstSet);
                secondSet = new Set(workoutExerciseId, exerciseId, 115, 5, workoutTime.toString());
                setDao.insertSet(secondSet);
                thirdSet = new Set(workoutExerciseId, exerciseId, 130, 5, workoutTime.toString());
                setDao.insertSet(thirdSet);

//                Third Exercise

                exerciseId = exercisesDao.getIdByName("Barbell Deadlift");
                workoutExercise = new WorkoutExercise(exerciseId,workoutId, exercise.getName());
                workoutExerciseDao.insert(workoutExercise);

                workoutExerciseId = workoutExerciseDao.findIdByExerciseAndWorkoutId(workoutId, exerciseId);

                firstSet = new Set(workoutExerciseId, exerciseId, 127, 8, workoutTime.toString());
                setDao.insertSet(firstSet);
                secondSet = new Set(workoutExerciseId, exerciseId, 137, 5, workoutTime.toString());
                setDao.insertSet(secondSet);
                thirdSet = new Set(workoutExerciseId, exerciseId, 151, 5, workoutTime.toString());
                setDao.insertSet(thirdSet);

//                Fourth Exercise

                exerciseId = exercisesDao.getIdByName("Barbell OHP");
                workoutExercise = new WorkoutExercise( exerciseId,workoutId, exercise.getName());
                workoutExerciseDao.insert(workoutExercise);

                workoutExerciseId = workoutExerciseDao.findIdByExerciseAndWorkoutId(workoutId, exerciseId);

                firstSet = new Set(workoutExerciseId, exerciseId, 45, 8, workoutTime.toString());
                setDao.insertSet(firstSet);
                secondSet = new Set(workoutExerciseId, exerciseId, 55, 5, workoutTime.toString());
                setDao.insertSet(secondSet);
                thirdSet = new Set(workoutExerciseId, exerciseId, 60, 5, workoutTime.toString());
                setDao.insertSet(thirdSet);

                //                fourth workout

                workoutTime = new Date(System.currentTimeMillis() - 259200000 * 3);
                workoutEndTime = new Date(System.currentTimeMillis() - 259200000 * 3 + 3600000);

                workout = new Workout(true, workoutTime.toString(), workoutEndTime.toString());
                workoutDao.insertWorkout(workout);

                workoutId = workoutDao.findByStartTime(workoutTime.toString());
                exerciseId = exercisesDao.getIdByName("Barbell Bench Press");


                workoutExercise = new WorkoutExercise(exerciseId, workoutId, exercise.getName());
                workoutExerciseDao.insert(workoutExercise);

                workoutExerciseId = workoutExerciseDao.findIdByExerciseAndWorkoutId(workoutId, exerciseId);
                firstSet = new Set(workoutExerciseId, exerciseId, 77, 8, workoutTime.toString());
                setDao.insertSet(firstSet);
                secondSet = new Set(workoutExerciseId, exerciseId, 87, 5, workoutTime.toString());
                setDao.insertSet(secondSet);
                thirdSet = new Set(workoutExerciseId, exerciseId, 95, 5, workoutTime.toString());
                setDao.insertSet(thirdSet);

//                Second Exercise

                exerciseId = exercisesDao.getIdByName("Barbell Squat");

                workoutExercise = new WorkoutExercise(exerciseId, workoutId, exercise.getName());
                workoutExerciseDao.insert(workoutExercise);

                workoutExerciseId = workoutExerciseDao.findIdByExerciseAndWorkoutId(workoutId, exerciseId);

                firstSet = new Set(workoutExerciseId, exerciseId, 106, 8, workoutTime.toString());
                setDao.insertSet(firstSet);
                secondSet = new Set(workoutExerciseId, exerciseId, 117, 5, workoutTime.toString());
                setDao.insertSet(secondSet);
                thirdSet = new Set(workoutExerciseId, exerciseId, 131, 5, workoutTime.toString());
                setDao.insertSet(thirdSet);

//                Third Exercise

                exerciseId = exercisesDao.getIdByName("Barbell Deadlift");
                workoutExercise = new WorkoutExercise(exerciseId,workoutId, exercise.getName());
                workoutExerciseDao.insert(workoutExercise);

                workoutExerciseId = workoutExerciseDao.findIdByExerciseAndWorkoutId(workoutId, exerciseId);

                firstSet = new Set(workoutExerciseId, exerciseId, 129, 8, workoutTime.toString());
                setDao.insertSet(firstSet);
                secondSet = new Set(workoutExerciseId, exerciseId, 147, 5, workoutTime.toString());
                setDao.insertSet(secondSet);
                thirdSet = new Set(workoutExerciseId, exerciseId, 161, 5, workoutTime.toString());
                setDao.insertSet(thirdSet);

//                Fourth Exercise

                exerciseId = exercisesDao.getIdByName("Barbell OHP");
                workoutExercise = new WorkoutExercise( exerciseId,workoutId, exercise.getName());
                workoutExerciseDao.insert(workoutExercise);

                workoutExerciseId = workoutExerciseDao.findIdByExerciseAndWorkoutId(workoutId, exerciseId);

                firstSet = new Set(workoutExerciseId, exerciseId, 45, 8, workoutTime.toString());
                setDao.insertSet(firstSet);
                secondSet = new Set(workoutExerciseId, exerciseId, 55, 5, workoutTime.toString());
                setDao.insertSet(secondSet);
                thirdSet = new Set(workoutExerciseId, exerciseId, 60, 5, workoutTime.toString());
                setDao.insertSet(thirdSet);



            });
        }
    };
}
