package com.example.progressiveoverloadfitness;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.progressiveoverloadfitness.database.Exercises;

public class ExerciseListAdaptor extends ListAdapter<Exercises, ExerciseViewHolder> {

    public ExerciseListAdaptor(@NonNull DiffUtil.ItemCallback<Exercises> diffCallBack){
        super(diffCallBack);
    }

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return ExerciseViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, int position){
        Exercises current = getItem(position);
        holder.bind(current.getName(), current.getBodyPart());
    }

    static class ExerciseDiff extends DiffUtil.ItemCallback<Exercises>{

        @Override
        public boolean areItemsTheSame(@NonNull Exercises oldItem, @NonNull Exercises newItem){
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Exercises oldItem, @NonNull Exercises newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }
}
