package com.example.progressiveoverloadfitness.views.history;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progressiveoverloadfitness.R;
import com.example.progressiveoverloadfitness.database.model.WorkoutWithWorkoutExercisesAndSets;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkoutListViewHolder extends RecyclerView.ViewHolder {
    private final TextView workoutDate;
    private RecyclerView exerciseRecycler;
    private Fragment fragment;

    private WorkoutListViewHolder(View itemView){
        super(itemView);
        workoutDate = itemView.findViewById(R.id.workout_date);
        exerciseRecycler = itemView.findViewById(R.id.history_workout_exercise_recycler);
    }

    public void bind(WorkoutWithWorkoutExercisesAndSets workoutWithWorkoutExercisesAndSets, Fragment fragment){
        this.fragment = fragment;
        Log.d("workout", workoutWithWorkoutExercisesAndSets.workout.startTime);
        Date date = new Date(workoutWithWorkoutExercisesAndSets.workout.startTime);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = formatter.format(date);

        workoutDate.setText(formattedDate);

        WorkoutExerciseListAdapter listAdapter = new WorkoutExerciseListAdapter(new WorkoutExerciseListAdapter.WorkoutExerciseDiff(), fragment);
        exerciseRecycler.setAdapter(listAdapter);
        exerciseRecycler.setLayoutManager(new LinearLayoutManager(fragment.getActivity()));
        listAdapter.submitList(workoutWithWorkoutExercisesAndSets.exercisesWithSets);
        //        SetRecyclerViewAdapter listAdapter = new SetRecyclerViewAdapter(new SetRecyclerViewAdapter.SetDiff(), exercisesWithSets.getSets());
//        setRecyclerView.setAdapter(listAdapter);
//        setRecyclerView.setLayoutManager(new LinearLayoutManager(fragment.getContext()));
//        listAdapter.submitList(exercisesWithSets.getSets());
    }

    static WorkoutListViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_workout_recyclerview, parent, false);

        return new WorkoutListViewHolder(view);
    }
}
