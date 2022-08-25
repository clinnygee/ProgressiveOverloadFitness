package com.example.progressiveoverloadfitness.views.dashboard;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.progressiveoverloadfitness.database.POFRepository;
import com.example.progressiveoverloadfitness.database.model.DashboardItemWithExercisesAndSets;
import com.example.progressiveoverloadfitness.database.model.Exercise;
import com.example.progressiveoverloadfitness.database.model.Set;

import java.util.ArrayList;
import java.util.List;

public class DashboardViewModel extends AndroidViewModel {
    private POFRepository mPOFRepository;
    private List<DashboardItemWithExercisesAndSets> allItems;

    public DashboardViewModel(Application application){
        super(application);
        mPOFRepository = new POFRepository(application);
        allItems = mPOFRepository.getAllDashboardItems();


    }

    public List<DashboardItemWithExercisesAndSets> getAllItems() {
        return this.allItems;
    }
}
