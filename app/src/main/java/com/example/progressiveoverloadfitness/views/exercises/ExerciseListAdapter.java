package com.example.progressiveoverloadfitness.views.exercises;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.progressiveoverloadfitness.database.model.Exercise;

public class ExerciseListAdapter extends ListAdapter<Exercise, ExerciseViewHolder> {
    private boolean select = false;
    ExercisesFragment fragment;

    public ExerciseListAdapter(@NonNull DiffUtil.ItemCallback<Exercise> diffCallBack){
        super(diffCallBack);
    }

    public ExerciseListAdapter(@NonNull DiffUtil.ItemCallback<Exercise> diffCallBack, ExercisesFragment fragment){
        super(diffCallBack);
        this.fragment = fragment;
        select = true;
    }

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return ExerciseViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position){
        Exercise current = getItem(position);
        holder.bind(current.getName(), current.getBodyPart());
        if(this.select){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment.addSelectedExercise(current.getName());
                }
            });
        }
    }

    public static class ExerciseDiff extends DiffUtil.ItemCallback<Exercise>{

        @Override
        public boolean areItemsTheSame(@NonNull Exercise oldItem, @NonNull Exercise newItem){
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Exercise oldItem, @NonNull Exercise newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }
}
