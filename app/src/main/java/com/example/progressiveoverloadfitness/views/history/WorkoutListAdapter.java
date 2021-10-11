package com.example.progressiveoverloadfitness.views.history;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.progressiveoverloadfitness.database.model.WorkoutWithWorkoutExercisesAndSets;

import org.jetbrains.annotations.NotNull;

public class WorkoutListAdapter extends ListAdapter<WorkoutWithWorkoutExercisesAndSets, WorkoutListViewHolder> {
    private Fragment fragment;
    public WorkoutListAdapter(@NonNull DiffUtil.ItemCallback<WorkoutWithWorkoutExercisesAndSets> diffCallBack){
        super(diffCallBack);
    }

    public WorkoutListAdapter(@NonNull DiffUtil.ItemCallback<WorkoutWithWorkoutExercisesAndSets> diffCallBack, Fragment fragment){
        super(diffCallBack);
        this.fragment = fragment;
    }

    @NonNull
    @NotNull
    @Override
    public WorkoutListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return WorkoutListViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull WorkoutListViewHolder holder, int position) {
        WorkoutWithWorkoutExercisesAndSets current = getItem(position);
        holder.bind(current, this.fragment);
    }

    public static class WorkoutDiff extends DiffUtil.ItemCallback<WorkoutWithWorkoutExercisesAndSets>{

        @Override
        public boolean areItemsTheSame(@NonNull WorkoutWithWorkoutExercisesAndSets oldItem, @NonNull WorkoutWithWorkoutExercisesAndSets newItem){
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull WorkoutWithWorkoutExercisesAndSets oldItem, @NonNull WorkoutWithWorkoutExercisesAndSets newItem) {
            return oldItem.getWorkoutId() == newItem.getWorkoutId();
        }

    }
}
