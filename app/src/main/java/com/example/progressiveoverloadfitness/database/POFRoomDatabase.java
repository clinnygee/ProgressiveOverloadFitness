package com.example.progressiveoverloadfitness.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.progressiveoverloadfitness.database.dao.ExercisesDao;
import com.example.progressiveoverloadfitness.database.dao.SetDao;
import com.example.progressiveoverloadfitness.database.dao.WorkoutDao;
import com.example.progressiveoverloadfitness.database.dao.WorkoutExerciseDao;
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
        Set.class},
        version = 4,
        exportSchema = false)
public abstract class POFRoomDatabase extends RoomDatabase {

    public abstract ExercisesDao exercisesDao();
//    public abstract WorkoutDao workoutDao();
//    public abstract WorkoutExerciseDao workoutExerciseDao();
//    public abstract SetDao setDao();

    private static volatile POFRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static POFRoomDatabase getDatabase(final Context context){
//        context.deleteDatabase("pof_database");
        if(INSTANCE == null){
            synchronized (POFRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            POFRoomDatabase.class, "pof_database").fallbackToDestructiveMigration().addCallback(sRoomDatabaseSeeder).build();
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

//                WorkoutDao workoutDao = INSTANCE.workoutDao();
//                WorkoutExerciseDao workoutExerciseDao = INSTANCE.workoutExerciseDao();
//                SetDao setDao = INSTANCE.setDao();

//                Workout workout = new Workout(true, new Date().toString(), new Date(System.currentTimeMillis() + 3600000).toString());
//                workoutDao.insertWorkout(workout);
                Exercise exercise = new Exercise("Barbell Bench Press", "", "Chest");
                exercisesDao.insert(exercise);

//                WorkoutExercise firstExercise = new WorkoutExercise(workout.id, exercise.getId());
//                workoutExerciseDao.insert(firstExercise);
//                Set firstSet = new Set(firstExercise.id, exercise.getId(), 40, 8);
//                setDao.insertSet(firstSet);
//                Set secondSet = new Set(firstExercise.id, exercise.getId(), 50, 5);
//                setDao.insertSet(secondSet);
//                Set thirdSet = new Set(firstExercise.id, exercise.getId(), 55, 5);
//                setDao.insertSet(thirdSet);

                exercise = new Exercise("Barbell Squat", "", "Legs");
                exercisesDao.insert(exercise);
                exercise = new Exercise("Barbell Deadlift", "", "Legs");
                exercisesDao.insert(exercise);
                exercise = new Exercise("Barbell OHP", "", "Shoulders");
                exercisesDao.insert(exercise);



            });
        }
    };
}
