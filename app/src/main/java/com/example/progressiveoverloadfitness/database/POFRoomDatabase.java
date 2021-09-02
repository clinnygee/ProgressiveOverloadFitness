package com.example.progressiveoverloadfitness.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Exercises.class}, version = 1, exportSchema = true)
public abstract class POFRoomDatabase extends RoomDatabase {

    public abstract ExercisesDao exercisesDao();

    private static volatile POFRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static POFRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (POFRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            POFRoomDatabase.class, "pof_database").addCallback(sRoomDatabaseSeeder).build();
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

                Exercises exercise = new Exercises("Barbell Bench Press", "", "Chest");
                exercisesDao.insert(exercise);
                exercise = new Exercises("Barbell Squat", "", "Legs");
                exercisesDao.insert(exercise);
                exercise = new Exercises("Barbell Deadlift", "", "Legs");
                exercisesDao.insert(exercise);
                exercise = new Exercises("Barbell OHP", "", "Shoulders");
                exercisesDao.insert(exercise);
            });
        }
    };
}
