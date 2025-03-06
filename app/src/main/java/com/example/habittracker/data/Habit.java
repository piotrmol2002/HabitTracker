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
    private String description;

    public Habit(String name, boolean isCompleted, int frequency, String description) {
        this.name = name;
        this.isCompleted = isCompleted;
        this.frequency = frequency;
        this.description = description;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public boolean isCompleted() { return isCompleted; }

    public String getName() { return name; }
    public void setCompleted(boolean completed) { isCompleted = completed; }
    @ColumnInfo(name = "frequency")
    private int frequency;
    public int getFrequency() {
        return frequency;
    }
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
