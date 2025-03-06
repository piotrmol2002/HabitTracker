package com.example.habittracker.ui;

import androidx.recyclerview.widget.DiffUtil;
import java.util.List;
import com.example.habittracker.data.Habit;

public class HabitDiffCallback extends DiffUtil.Callback {
    private final List<Habit> oldList;
    private final List<Habit> newList;

    public HabitDiffCallback(List<Habit> oldList, List<Habit> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
