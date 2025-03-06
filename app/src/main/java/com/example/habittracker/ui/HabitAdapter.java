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
        holder.textViewEndDate.setText(habit.getEndDate() != null ? "End Date: " + habit.getEndDate().toString() : "No end date set");

        StringBuilder completionStatus = new StringBuilder();
        for (HabitDay habitDay : habit.getHabitDays()) {
            completionStatus.append(habitDay.getDate().toString())
                    .append(" - ")
                    .append(habitDay.isComplete() ? "Completed" : "Not Completed")
                    .append("\n");
        }
        //holder.textViewCompletionStatus.setText(completionStatus.toString());

        // Ustawianie stanu checkboxa
        holder.checkBoxHabit.setChecked(habit.isCompleted());

        // Zarządzanie dostępnością checkboxa na podstawie daty
        holder.checkBoxHabit.setEnabled(habit.shouldAllowCheckboxForToday()); // np. sprawdzenie, czy dzisiaj można zaznaczyć

        // Obsługa kliknięcia checkboxa
        holder.checkBoxHabit.setOnClickListener(v -> {
            org.threeten.bp.LocalDate today = org.threeten.bp.LocalDate.now();

            // Znalezienie HabitDay dla dzisiejszego dnia
            HabitDay habitDay = null;
            for (HabitDay day : habit.getHabitDays()) {
                if (day.getDate().equals(today)) {
                    habitDay = day;
                    break;
                }
            }

            // Jeśli znaleziono dzisiejszy HabitDay, zaktualizuj jego status
            if (habitDay != null) {
                habitDay.setComplete(true); // Ustaw status na wykonany
            }

            viewModel.updateHabit(habit); // Zaktualizuj nawyk w bazie danych
            holder.checkBoxHabit.setEnabled(false); // Zablokuj checkboxa
        });
        // Obsługuje przypadek, kiedy checkbox nie powinien być dostępny (np. tylko w określone dni)
        if (!habit.shouldAllowCheckboxForToday()) {
            holder.checkBoxHabit.setClickable(false);  // Dodatkowo możemy uczynić checkbox nieklikalnym
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
        //TextView textViewCompletionStatus;
        TextView textViewEndDate;
        TextView textViewHabitName;
        TextView textViewHabitDescription;
        CheckBox checkBoxHabit;

        public HabitViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHabitName = itemView.findViewById(R.id.text_view_habit_name);
            textViewHabitDescription = itemView.findViewById(R.id.text_view_habit_description);
            textViewEndDate = itemView.findViewById(R.id.text_view_end_date);
            //textViewCompletionStatus = itemView.findViewById(R.id.text_view_completion_status);
            checkBoxHabit = itemView.findViewById(R.id.checkBoxHabit);
        }
    }
}
