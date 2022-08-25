package com.example.progressiveoverloadfitness.views.workout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progressiveoverloadfitness.R;


class PerformWorkoutListViewHolder extends RecyclerView.ViewHolder{
    private final TextView exerciseName;
    private final Button addSet;
    private RecyclerView setRecyclerView;
    PerformWorkoutFragment fragment;

    private PerformWorkoutListViewHolder(View itemView){
        super(itemView);
        exerciseName = itemView.findViewById(R.id.exercise_name);
        addSet = itemView.findViewById(R.id.add_set_button);
        setRecyclerView = itemView.findViewById(R.id.set_recycler);
    }

    public void bind(ExercisesWithSets exercisesWithSets, PerformWorkoutFragment fragment){
        exerciseName.setText(exercisesWithSets.getName());
        this.fragment = fragment;
        addSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.addSetDialog(exercisesWithSets.getName());
            }
        });
        SetRecyclerViewAdapter listAdapter = new SetRecyclerViewAdapter(new SetRecyclerViewAdapter.SetDiff(), exercisesWithSets.getSets());
        setRecyclerView.setAdapter(listAdapter);
        setRecyclerView.setLayoutManager(new LinearLayoutManager(fragment.getContext()));
        listAdapter.submitList(exercisesWithSets.getSets());
    }

    static PerformWorkoutListViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.perform_workout_recyclerview, parent, false);
        return new PerformWorkoutListViewHolder(view);
    }
}
