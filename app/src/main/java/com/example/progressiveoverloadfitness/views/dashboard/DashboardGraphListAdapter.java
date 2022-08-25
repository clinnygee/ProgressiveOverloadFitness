package com.example.progressiveoverloadfitness.views.dashboard;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progressiveoverloadfitness.R;
import com.example.progressiveoverloadfitness.database.model.DashboardItemWithExercisesAndSets;
import com.example.progressiveoverloadfitness.database.model.Set;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.EntryXComparator;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DashboardGraphListAdapter extends ListAdapter<DashboardItemWithExercisesAndSets, DashboardGraphListHolder> {

    public DashboardGraphListAdapter(DiffUtil.ItemCallback dashboardItemCallBack){
        super(dashboardItemCallBack);
    }

    @NonNull
    @NotNull
    @Override
    public DashboardGraphListHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return DashboardGraphListHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DashboardGraphListHolder holder, int position) {
        DashboardItemWithExercisesAndSets current = getItem(position);
        holder.bind(current);
    }

    public static class DashboardDiff extends DiffUtil.ItemCallback<DashboardItemWithExercisesAndSets>{
        @Override
        public boolean areItemsTheSame(@NonNull DashboardItemWithExercisesAndSets oldItem, @NonNull DashboardItemWithExercisesAndSets newItem){
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull DashboardItemWithExercisesAndSets oldItem, @NonNull DashboardItemWithExercisesAndSets newItem) {
            return oldItem.dashboardItem.id == newItem.dashboardItem.id;
        }
    }
}

class DashboardGraphListHolder extends RecyclerView.ViewHolder{
    private LineChart lineChart;
    private TextView title;
    private DashboardItemWithExercisesAndSets dashboardItem;

    DashboardGraphListHolder(View itemView){
        super(itemView);
        lineChart = itemView.findViewById(R.id.line_chart);
        title = itemView.findViewById(R.id.graph_title);
    }

    public void bind(DashboardItemWithExercisesAndSets dashboardItem){
        this.dashboardItem = dashboardItem;
//        lineChart.setDescription(dashboardItem.exerciseWithSets.get(0).exercise.getName().toString());
        title.setText(dashboardItem.exerciseWithSets.exercise.getName());
        configureChart();
    }

    static DashboardGraphListHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dashboard_graph_recyclerview, parent, false);
        return new DashboardGraphListHolder(view);
    }

    private void configureChart(){
//        Description description = new Description();
//        description.setText(this.dashboardItem.exerciseWithSets.exercise.getName());
//        description.setTextSize(28);
////        description.setTextColor();
//        lineChart.setDescription(description);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            private final SimpleDateFormat mFormat = new SimpleDateFormat("dd/MM", Locale.ENGLISH);

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
//                long millis = value;
                return mFormat.format(new Date((long)value));
            }
        });

        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setDrawGridLines(true);
        yAxis.setDrawAxisLine(true);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);
        setChartData();
    }

    private void setChartData(){
        ArrayList<Entry> values = new ArrayList<>();
        final SimpleDateFormat mFormat = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        for (Set set : dashboardItem.exerciseWithSets.sets) {
//            long x = new Date(set.day).getTime();
//            long y = calculateOneRepMax(set.weight, set.reps);
            Entry entry = new Entry(new Date(set.day).getTime(), calculateOneRepMax(set.weight, set.reps));
            values.add(entry);

        }
        Log.d("size", String.valueOf(values.size()));
        Collections.sort(values, new EntryXComparator());
        LineDataSet set = new LineDataSet(values, "e1rm");
        set.setColors(ColorTemplate.JOYFUL_COLORS);
        set.setLineWidth(4);
//        set.setDrawValues(true);
//        set.setDrawCircles(true);

        LineData data = new LineData(set);

        lineChart.setData(data);
//        entryList.add(new Entry(10,20));
//        entryList.add(new Entry(5,10));
//        entryList.add(new Entry(7,31));
//        entryList.add(new Entry(3,14));
//        LineDataSet lineDataSet = new LineDataSet(entryList,"country");
//        lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
//        lineDataSet.setFillAlpha(110);
//        LineData lineData = new LineData(lineDataSet);
//        lineChart.setData(lineData);
//        lineChart.setVisibleXRangeMaximum(10);
//        lineChart.invalidate();

    }

    private float calculateOneRepMax(double weight, int reps){
        double orm = weight * (1+(0.033 * reps));
        return (float) orm;
    }
}

//class LineChartXAxisValueFormatter extends IndexAxisValueFormatter {
//
//    @Override
//    public String getFormattedValue(float value){
//
//    }
//}
