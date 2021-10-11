package com.example.progressiveoverloadfitness.views.workout;

import android.content.Context;
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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SetRecyclerViewAdapter extends ListAdapter<Set, SetViewHolder> {

    public ArrayList<Set> setArrayList;
    Context cxt;


    public SetRecyclerViewAdapter(@NonNull DiffUtil.ItemCallback<Set> diffCallBack) {
        super(diffCallBack);

    }
    public SetRecyclerViewAdapter(@NonNull DiffUtil.ItemCallback<Set> diffCallBack, ArrayList<Set> sets) {
        super(diffCallBack);
        this.setArrayList = sets;
    }

    @NonNull
    @NotNull
    @Override
    public SetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return SetViewHolder.create(parent);
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.set_recyclerview, parent, false);
//        return new SetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SetViewHolder holder, int position) {
//        Set currentItem = setArrayList.get(position);
//        holder.setNumber.setText(position + 1);
//        holder.setWeight.setText(String.valueOf(currentItem.weight));
//        holder.setReps.setText(String.valueOf(currentItem.reps));
        Set current = getItem(position);
        holder.bind(current, position);
//        holder.heroImage.setImageResource(currentItem.getHeroImage());
//        holder.movieName.setText(currentItem.getMovieName());

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
    @Override
    public int getItemCount() {
        return setArrayList.size();
    }
}
