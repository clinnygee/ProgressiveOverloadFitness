package com.example.progressiveoverloadfitness;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.progressiveoverloadfitness.database.model.Exercise;

public class ExerciseListAdaptor extends ListAdapter<Exercise, ExerciseViewHolder> {

    public ExerciseListAdaptor(@NonNull DiffUtil.ItemCallback<Exercise> diffCallBack){
        super(diffCallBack);
    }

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return ExerciseViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position){
        Exercise current = getItem(position);
        holder.bind(current.getName(), current.getBodyPart());
    }

    static class ExerciseDiff extends DiffUtil.ItemCallback<Exercise>{

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
