package com.example.habittracker.data;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.threeten.bp.LocalDate;

@Entity(tableName = "habit_days")
public class HabitDay {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int habitId;
    private LocalDate date;
    private boolean isComplete;

    public HabitDay(int habitId, LocalDate date, boolean isComplete) {
        this.habitId = habitId;
        this.date = date;
        this.isComplete = isComplete;
    }

    // Gettery i Settery

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getHabitId() { return habitId; }
    public void setHabitId(int habitId) { this.habitId = habitId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public boolean isComplete() { return isComplete; }
    public void setComplete(boolean complete) { isComplete = complete; }
}
