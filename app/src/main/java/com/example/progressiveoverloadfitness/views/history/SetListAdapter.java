package com.example.progressiveoverloadfitness.views.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progressiveoverloadfitness.R;
import com.example.progressiveoverloadfitness.database.model.Set;
import com.example.progressiveoverloadfitness.database.model.WorkoutExercisesWithSets;

import org.jetbrains.annotations.NotNull;

public class SetListAdapter extends ListAdapter<Set, SetListViewHolder> {

    public SetListAdapter(DiffUtil.ItemCallback<Set> setItemCallback){
        super(setItemCallback);
    }

    @NonNull
    @NotNull
    @Override
    public SetListViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return SetListViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SetListViewHolder holder, int position) {
        Set current = getItem(position);
        holder.bind(current);
    }

    public static class SetDiff extends DiffUtil.ItemCallback<Set>{
        @Override
        public boolean areItemsTheSame(@NonNull Set oldItem, @NonNull Set newItem){
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Set oldItem, @NonNull Set newItem) {
            return oldItem.id == newItem.id;
        }
    }
}

class SetListViewHolder extends RecyclerView.ViewHolder {
    private TextView weightAndReps;

    private SetListViewHolder(View itemView){
        super(itemView);
        weightAndReps = itemView.findViewById(R.id.history_set_item);
    }

    public void bind(Set set){
        String information = String.valueOf(set.weight) + " x " + String.valueOf(set.reps);
        weightAndReps.setText(information);
    }

    static SetListViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_workout_set_recyclerview, parent, false);
        return new SetListViewHolder(view);
    }
}
