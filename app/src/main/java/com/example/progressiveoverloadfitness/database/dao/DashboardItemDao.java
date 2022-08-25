package com.example.progressiveoverloadfitness.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.progressiveoverloadfitness.database.model.DashboardItem;
import com.example.progressiveoverloadfitness.database.model.DashboardItemWithExercisesAndSets;

import java.util.List;

@Dao
public interface DashboardItemDao {

    @Insert
    void insert(DashboardItem item);

    @Transaction
    @Query("SELECT * FROM dashboard_item")
    List<DashboardItemWithExercisesAndSets> getAllDashboardItems();
}
