package com.example.progressiveoverloadfitness.views.workout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.progressiveoverloadfitness.R;
import com.example.progressiveoverloadfitness.database.model.Set;

class SetViewHolder extends RecyclerView.ViewHolder{
    public TextView setNumber;
    public TextView setWeight;
    public TextView setReps;

    public SetViewHolder(View itemView) {
        super(itemView);
        setNumber = itemView.findViewById(R.id.set_number);
        setWeight = itemView.findViewById(R.id.weight);
        setReps = itemView.findViewById(R.id.reps);
    }

    public void bind(Set set, int position){
//        exerciseName.setText(exercisesWithSets.getName());
//        this.fragment = fragment;
        setNumber.setText(String.valueOf(position));
        setWeight.setText(String.valueOf(set.weight));
        setReps.setText(String.valueOf(set.reps));
    }

    static SetViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.set_recyclerview, parent, false);
        return new SetViewHolder(view);
    }
}
