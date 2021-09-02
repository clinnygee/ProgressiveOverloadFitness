package com.example.progressiveoverloadfitness;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class ExerciseViewHolder extends RecyclerView.ViewHolder{
    private final TextView exerciseTitle;
    private final TextView exerciseBodyPart;

    private ExerciseViewHolder(View itemView){
        super(itemView);
        exerciseTitle = itemView.findViewById(R.id.exerciseTitle);
        exerciseBodyPart = itemView.findViewById(R.id.exerciseBodyPart);
    }

    public void bind(String name, String bodyPart){
        exerciseTitle.setText(name);
        exerciseBodyPart.setText(bodyPart);
    }

    static ExerciseViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view, parent, false);
        return new ExerciseViewHolder(view);
    }
}
