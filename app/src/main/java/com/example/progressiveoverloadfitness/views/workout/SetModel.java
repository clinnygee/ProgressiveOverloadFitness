package com.example.progressiveoverloadfitness.views.workout;

public class SetModel implements Cloneable{
    public int id;
    public int exercise_id;
    public int workoutExerciseId;
    public double weight;
    public int reps;

    public SetModel(int id, int exercise_id, int workoutExerciseId, double weight, int reps){
        this.id = id;
        this.exercise_id = exercise_id;
        this.workoutExerciseId = workoutExerciseId;
        this.weight = weight;
        this.reps = reps;
    }

    public SetModel clone(){
        return new SetModel(this.id, this.exercise_id, this.workoutExerciseId, this.weight, this.reps);
    }
}
