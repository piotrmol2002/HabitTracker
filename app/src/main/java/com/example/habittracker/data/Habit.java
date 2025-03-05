package com.example.habittracker.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "habit_table")
public class Habit {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private final String name;
    private final String schedule; // np. "codziennie", "pon/wt/czw"
    private boolean isCompleted; // Czy nawyk został wykonany dziś?

    public Habit(String name, String schedule, boolean isCompleted) {
        this.name = name;
        this.schedule = schedule;
        this.isCompleted = isCompleted;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public String getSchedule() { return schedule; }
    public boolean isCompleted() { return isCompleted; }

    public void setCompleted(boolean completed) { isCompleted = completed; }
}
