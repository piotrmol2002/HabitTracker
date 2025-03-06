package com.example.habittracker.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;

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
    @TypeConverters(HabitDayConverter.class)
    private ArrayList<HabitDay> habitDays;

    public Habit(String name, boolean isCompleted, int frequency, String description, org.threeten.bp.LocalDate endDate, ArrayList<HabitDay> habitDays) {
        this.name = name;
        this.isCompleted = isCompleted;
        this.frequency = frequency;
        this.description = description;
        this.endDate = endDate;
        this.habitDays = habitDays;
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

    public ArrayList<HabitDay> getHabitDays() {
        return habitDays;
    }

    public void setHabitDays(ArrayList<HabitDay> habitDays) {
        this.habitDays = new ArrayList<>(habitDays);  // Konwersja na ArrayList
    }

    public boolean shouldAllowCheckboxForToday() {
        org.threeten.bp.LocalDate today = org.threeten.bp.LocalDate.now();
        // Można tu dodać dowolną logikę, np. czy dzisiaj przypada nawyk
        return this.getEndDate() != null && !today.isAfter(this.getEndDate());
    }

}
