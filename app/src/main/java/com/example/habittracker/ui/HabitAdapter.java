package com.example.habittracker.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.habittracker.R;
import com.example.habittracker.data.Habit;
import java.util.List;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.HabitViewHolder> {
    private final List<Habit> habitList;

    public HabitAdapter(List<Habit> habitList) {
        this.habitList = habitList;
    }

    @NonNull
    @Override
    public HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_habit, parent, false);
        return new HabitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitViewHolder holder, int position) {
        Habit habit = habitList.get(position);
        holder.textViewHabitName.setText(habit.getName());
        holder.textViewHabitDescription.setText(habit.getDescription());
        holder.textViewHabitName.setText(habit.getName());

        if (habit.getEndDate() != null) {
            String formattedEndDate = habit.getEndDate().toString(); // Zakładając, że masz endDate jako LocalDate
            holder.textViewEndDate.setText("End Date: " + formattedEndDate);
        } else {
            holder.textViewEndDate.setText("No end date set");
        }
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }

    public void setHabits(List<Habit> newHabits) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new HabitDiffCallback(habitList, newHabits));
        habitList.clear();
        habitList.addAll(newHabits);
        diffResult.dispatchUpdatesTo(this);
    }


    public static class HabitViewHolder extends RecyclerView.ViewHolder {
        TextView textViewEndDate;
        TextView textViewHabitName;
        TextView textViewHabitDescription;

        public HabitViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHabitName = itemView.findViewById(R.id.text_view_habit_name);
            textViewHabitDescription = itemView.findViewById(R.id.text_view_habit_description);
            textViewEndDate = itemView.findViewById(R.id.text_view_end_date);
        }
    }
}
