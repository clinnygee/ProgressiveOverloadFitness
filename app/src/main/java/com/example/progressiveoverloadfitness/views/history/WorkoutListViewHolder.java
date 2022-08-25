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
import com.example.progressiveoverloadfitness.database.model.Set;
import com.example.progressiveoverloadfitness.database.model.WorkoutExercisesWithSets;
import com.example.progressiveoverloadfitness.database.model.WorkoutWithWorkoutExercisesAndSets;
import com.example.progressiveoverloadfitness.views.dashboard.ExercisesWithSets;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WorkoutListViewHolder extends RecyclerView.ViewHolder {
    private final TextView workoutDate;
    private final TextView workoutDuration;
    private final TextView totalVolume;
    private RecyclerView exerciseRecycler;
    private Fragment fragment;

    private WorkoutListViewHolder(View itemView){
        super(itemView);
        workoutDuration = itemView.findViewById(R.id.workout_duration);
        totalVolume = itemView.findViewById(R.id.workout_volume);
        workoutDate = itemView.findViewById(R.id.workout_date);
        exerciseRecycler = itemView.findViewById(R.id.history_workout_exercise_recycler);
    }

    public void bind(WorkoutWithWorkoutExercisesAndSets workoutWithWorkoutExercisesAndSets, Fragment fragment){
        this.fragment = fragment;
        Date startTime = new Date(workoutWithWorkoutExercisesAndSets.workout.startTime);
        Date endTime = new Date(workoutWithWorkoutExercisesAndSets.workout.endTime);
        Log.d("workout", workoutWithWorkoutExercisesAndSets.workout.startTime);
        Date date = new Date(workoutWithWorkoutExercisesAndSets.workout.startTime);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = formatter.format(startTime);

        long millis = endTime.getTime() - startTime.getTime();

        String timeElapsed = String.format("Duration: %02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), // The change is in this line
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

        double totalVolumeNumber = getTotalVolume(workoutWithWorkoutExercisesAndSets.exercisesWithSets);
        totalVolume.setText("Total Volume: " + String.valueOf(totalVolumeNumber) + "kg");
        workoutDuration.setText(timeElapsed);

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

    public double getTotalVolume(List<WorkoutExercisesWithSets> exercisesWithSets){

        double total = 0;
        for (WorkoutExercisesWithSets exercisesWithSet : exercisesWithSets) {
            for (Set set : exercisesWithSet.sets) {
                total += set.reps * set.weight;
            }
        }
        return total;
    }
}
