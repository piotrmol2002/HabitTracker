package com.example.habittracker.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.habittracker.R;
import com.example.habittracker.data.Habit;
import com.example.habittracker.data.HabitDay;
import com.example.habittracker.viewmodel.HabitViewModel;

import java.util.List;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.HabitViewHolder> {
    private final List<Habit> habitList;
    private final HabitViewModel viewModel;

    public HabitAdapter(List<Habit> habitList, HabitViewModel viewModel) {
        this.habitList = habitList;
        this.viewModel = viewModel;
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
        holder.textViewEndDate.setText(habit.getEndDate().toString());
        holder.checkBoxHabit.setChecked(habit.isCompleted());

        if (habit.getEndDate() != null) {
            String formattedEndDate = habit.getEndDate().toString(); // Zakładając, że masz endDate jako LocalDate
            holder.textViewEndDate.setText("End Date: " + formattedEndDate);
        } else {
            holder.textViewEndDate.setText("No end date set");
        }
        StringBuilder completionStatus = new StringBuilder();
        for (HabitDay habitDay : habit.getHabitDays()) {
            completionStatus.append(habitDay.getDate().toString()).append(" - ").append(habitDay.isComplete() ? "Completed" : "Not Completed").append("\n");
        }
        holder.textViewCompletionStatus.setText(completionStatus.toString());

        holder.checkBoxHabit.setOnClickListener(v -> {
            org.threeten.bp.LocalDate today = org.threeten.bp.LocalDate.now();
            viewModel.markHabitAsComplete(habit, today);
            holder.checkBoxHabit.setEnabled(false); // zablokowanie checkboxa po zaznaczeniu
        }); // ogarnac logike checkboxa

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
        TextView textViewCompletionStatus;
        TextView textViewEndDate;
        TextView textViewHabitName;
        TextView textViewHabitDescription;
        CheckBox checkBoxHabit;

        public HabitViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHabitName = itemView.findViewById(R.id.text_view_habit_name);
            textViewHabitDescription = itemView.findViewById(R.id.text_view_habit_description);
            textViewEndDate = itemView.findViewById(R.id.text_view_end_date);
            checkBoxHabit = itemView.findViewById(R.id.checkBoxHabit);
        }
    }
}
