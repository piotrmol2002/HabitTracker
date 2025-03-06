package com.example.habittracker.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import org.threeten.bp.LocalDate;

@Entity(tableName = "habit_table")
public class Habit {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private final String name;
    private boolean isCompleted;
    private String description;
    @TypeConverters(DateConverter.class)
    @ColumnInfo(name = "end_date")
    private LocalDate endDate;

    public Habit(String name, boolean isCompleted, int frequency, String description, org.threeten.bp.LocalDate endDate) {
        this.name = name;
        this.isCompleted = isCompleted;
        this.frequency = frequency;
        this.description = description;
        this.endDate = endDate;
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

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

}
