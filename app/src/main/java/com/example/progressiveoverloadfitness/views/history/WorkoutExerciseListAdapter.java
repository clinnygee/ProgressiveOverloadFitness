package com.example.progressiveoverloadfitness.views.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progressiveoverloadfitness.R;
import com.example.progressiveoverloadfitness.database.model.WorkoutExercisesWithSets;

import org.jetbrains.annotations.NotNull;

public class WorkoutExerciseListAdapter extends ListAdapter<WorkoutExercisesWithSets, WorkoutExerciseListViewHolder> {
    Fragment fragment;


    public WorkoutExerciseListAdapter(DiffUtil.ItemCallback<WorkoutExercisesWithSets> workoutExercisesWithSetsItemCallback, Fragment fragment){
        super(workoutExercisesWithSetsItemCallback);
        this.fragment = fragment;
    }

    @NonNull
    @NotNull
    @Override
    public WorkoutExerciseListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return WorkoutExerciseListViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull WorkoutExerciseListViewHolder holder, int position) {
        WorkoutExercisesWithSets current = getItem(position);
        holder.bind(current, this.fragment);
    }

    public static class WorkoutExerciseDiff extends DiffUtil.ItemCallback<WorkoutExercisesWithSets>{
        @Override
        public boolean areItemsTheSame(@NonNull WorkoutExercisesWithSets oldItem, @NonNull WorkoutExercisesWithSets newItem){
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull WorkoutExercisesWithSets oldItem, @NonNull WorkoutExercisesWithSets newItem) {
            return oldItem.workoutExercises.getId() == newItem.workoutExercises.getId();
        }
    }
}

 class WorkoutExerciseListViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private Fragment fragment;
    private RecyclerView setRecycler;
    private WorkoutExerciseListViewHolder(View itemView){
        super(itemView);
        title = itemView.findViewById(R.id.history_exercise_name);
        setRecycler = itemView.findViewById(R.id.history_set_recyclerview);
    }

    public void bind(WorkoutExercisesWithSets workoutExerciseWithSets, Fragment fragment){
        this.fragment = fragment;
        title.setText(workoutExerciseWithSets.workoutExercises.getName());
        SetListAdapter setListAdapter = new SetListAdapter(new SetListAdapter.SetDiff());
        setRecycler.setAdapter(setListAdapter);
        setRecycler.setLayoutManager(new LinearLayoutManager(fragment.getActivity()));
        setListAdapter.submitList(workoutExerciseWithSets.sets);
    }
    static WorkoutExerciseListViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_workout_exercise_recyclerview, parent, false);
        return new WorkoutExerciseListViewHolder(view);
    }
}
