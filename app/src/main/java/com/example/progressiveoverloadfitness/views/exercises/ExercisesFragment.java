package com.example.progressiveoverloadfitness.views.exercises;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.progressiveoverloadfitness.R;
import com.example.progressiveoverloadfitness.database.POFViewModel;
import com.example.progressiveoverloadfitness.views.workout.WorkoutActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExercisesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExercisesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String SELECT = "select";
    Button backButton;
    private RecyclerView recyclerView;
    private POFViewModel mPOFViewModel;

    // TODO: Rename and change types of parameters
    private boolean mSelect = false;
    private String mParam2;

    public ExercisesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment ExercisesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExercisesFragment newInstance(String select) {
        ExercisesFragment fragment = new ExercisesFragment();
        Bundle args = new Bundle();
        Log.d("constructor", select);
        args.putString(SELECT, select);
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Log.d("args", getArguments().toString());
            Log.d("constructor", "in the if");
            Bundle args = getArguments();
            if(getArguments().getString(SELECT).equals("true")){
                mSelect = true;
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exercises, container, false);

        getActivity().setTitle("Exercises");
        recyclerView = view.findViewById(R.id.recyclerview);
        ExerciseListAdapter adapter;
        Log.d("onCreateView", mSelect ? "true" : "null");
        if(mSelect){
            adapter = new ExerciseListAdapter(new ExerciseListAdapter.ExerciseDiff(), this );
            backButton = view.findViewById(R.id.exercise_back_button);
            backButton.setVisibility(View.VISIBLE);
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    back();
                }
            });
        } else {
            adapter = new ExerciseListAdapter(new ExerciseListAdapter.ExerciseDiff());
        }

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        Get a new or existing ViewModel from the ViewModelProvider
        mPOFViewModel = new ViewModelProvider(getActivity()).get(POFViewModel.class);

//        Add an observer on the LivaData return by getAlphabetizedExercises
//        The onChanged method fires when the observed data changes, and the fragment is in
//        the foreground
        mPOFViewModel.getAllExercises().observe(getActivity(), exercises -> {
            adapter.submitList(exercises);
        });

        return view;
    }
    public void addSelectedExercise(String name){
        ((WorkoutActivity)getActivity()).addSelectedExercise(name);
    }

    public void back(){
        ((WorkoutActivity)getActivity()).backButton();
    }
}