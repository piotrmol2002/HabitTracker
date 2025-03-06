package com.example.habittracker.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "habit_table")
public class Habit {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private final String name;
    private boolean isCompleted;

    public Habit(String name, boolean isCompleted, int frequency) {
        this.name = name;
        this.isCompleted = isCompleted;
        this.frequency = frequency;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }

    @ColumnInfo(name = "frequency")
    private int frequency;

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

}
