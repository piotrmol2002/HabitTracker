package com.example.habittracker.data;

import org.threeten.bp.LocalDate;

public class HabitDay {
    private LocalDate date;
    private boolean isComplete;

    public HabitDay(LocalDate date, boolean isComplete) {
        this.date = date;
        this.isComplete = isComplete;
    }

    // Gettery i settery

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }
}

