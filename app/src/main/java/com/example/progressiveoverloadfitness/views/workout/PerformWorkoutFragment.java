package com.example.progressiveoverloadfitness.views.workout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.progressiveoverloadfitness.R;
import com.example.progressiveoverloadfitness.database.model.Set;
import com.example.progressiveoverloadfitness.database.model.Workout;
import com.example.progressiveoverloadfitness.database.model.WorkoutExercise;
import com.example.progressiveoverloadfitness.views.exercises.ExerciseListAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerformWorkoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerformWorkoutFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button cancelWorkout;
    Button addExercise;
    Button finishWorkout;
    WorkoutViewModel WOVM;
    RecyclerView recyclerView;
    TextView timer;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PerformWorkoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PerformWorkoutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PerformWorkoutFragment newInstance(String param1, String param2) {
        PerformWorkoutFragment fragment = new PerformWorkoutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perform_workout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);


        cancelWorkout = view.findViewById(R.id.cancel_workout);
        finishWorkout = view.findViewById(R.id.finish_workout);
        addExercise = view.findViewById(R.id.add_exercise);
        timer = view.findViewById(R.id.timer);
        startTimer();
        this.WOVM = ((WorkoutActivity)getActivity()).getViewModel();


        recyclerView = view.findViewById(R.id.perform_exercise_recycler);
        PerformWorkoutListAdapter listAdapter = new PerformWorkoutListAdapter(new PerformWorkoutListAdapter.ExerciseDiff(), this);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        WOVM.getExercisesWithSets().observe((getActivity()), exerciseWithSet -> {
            listAdapter.submitList(exerciseWithSet);
        });

//        WOVM.addExercise("Barbell Squat");

        addExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WorkoutActivity)getActivity()).addExercise();
            }
        });
        finishWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WorkoutActivity)getActivity()).endWorkout();
            }
        });
        cancelWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WorkoutActivity)getActivity()).endWorkout();
            }
        });
    }

    public void addSetDialog(String exerciseName){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        Context context = getActivity();
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        builder.setTitle(exerciseName);
        final EditText weight = new EditText(context);
        weight.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        weight.setHint("weight");
        final EditText reps = new EditText(context);
        reps.setInputType(InputType.TYPE_CLASS_NUMBER);
        reps.setHint("reps");
        layout.addView(weight);
        layout.addView(reps);
        builder.setView(layout);
        double weightValue = 0;
        int repValue = 0;

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                WOVM.addSet(exerciseName, Integer.parseInt(reps.getText().toString()), Double.parseDouble(weight.getText().toString()));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void startTimer(){

        final Handler handler = new Handler();
//        long elapsed = 0;
        handler.post(new Runnable() {
            long millis = 0;
            TextView timeView = timer;
            @Override
            public void run() {
                millis += 1000;
                String elapsedString = String.format("%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(millis),
                        TimeUnit.MILLISECONDS.toMinutes(millis) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), // The change is in this line
                        TimeUnit.MILLISECONDS.toSeconds(millis) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                timeView.setText(elapsedString);
                handler.postDelayed(this, 1000);
            }
        });
    }

    public void updateTimer(){

    }
}