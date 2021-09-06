package com.example.progressiveoverloadfitness.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.progressiveoverloadfitness.database.dao.ExercisesDao;
import com.example.progressiveoverloadfitness.database.model.Exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Exercise.class}, version = 2, exportSchema = false)
public abstract class POFRoomDatabase extends RoomDatabase {

    public abstract ExercisesDao exercisesDao();

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

                Exercise exercise = new Exercise("Barbell Bench Press", "", "Chest");
                exercisesDao.insert(exercise);
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
