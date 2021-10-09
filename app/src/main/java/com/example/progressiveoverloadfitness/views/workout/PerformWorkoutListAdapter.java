package com.example.progressiveoverloadfitness.views.workout;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progressiveoverloadfitness.database.model.WorkoutExercise;
import com.example.progressiveoverloadfitness.database.model.WorkoutExercise;
import com.example.progressiveoverloadfitness.views.exercises.ExercisesFragment;

import org.jetbrains.annotations.NotNull;

public class PerformWorkoutListAdapter extends ListAdapter<ExercisesWithSets, PerformWorkoutListViewHolder> {
    PerformWorkoutFragment fragment;
    public PerformWorkoutListAdapter(@NonNull DiffUtil.ItemCallback<ExercisesWithSets> diffCallBack){
        super(diffCallBack);
    }

    public PerformWorkoutListAdapter(@NonNull DiffUtil.ItemCallback<ExercisesWithSets> diffCallBack, PerformWorkoutFragment fragment){
        super(diffCallBack);
        this.fragment = fragment;
    }

    @NonNull
    @NotNull
    @Override
    public PerformWorkoutListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return PerformWorkoutListViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PerformWorkoutListViewHolder holder, int position) {
        ExercisesWithSets current = getItem(position);
        holder.bind(current, this.fragment);
    }

    public static class ExerciseDiff extends DiffUtil.ItemCallback<ExercisesWithSets>{

        @Override
        public boolean areItemsTheSame(@NonNull ExercisesWithSets oldItem, @NonNull ExercisesWithSets newItem){
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ExercisesWithSets oldItem, @NonNull ExercisesWithSets newItem) {
            return oldItem.getName().equals(newItem.getName());
        }

    }
}
