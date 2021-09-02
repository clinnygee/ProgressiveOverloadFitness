package com.example.progressiveoverloadfitness;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class ExerciseViewHolder extends RecyclerView.ViewHolder{
    private final TextView exerciseItemView;

    private ExerciseViewHolder(View itemView){
        super(itemView);
        exerciseItemView = itemView.findViewById(R.id.exercisesTextView);
    }

    public void bind(String name, String type){
        exerciseItemView.setText(name + " " + type);
    }

    static ExerciseViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view, parent, false);
        return new ExerciseViewHolder(view);
    }
}
