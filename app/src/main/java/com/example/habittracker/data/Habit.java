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
    private String name;
    private String description;
    @TypeConverters(DateConverter.class)
    @ColumnInfo(name = "end_date")
    private LocalDate endDate;
    @ColumnInfo(name = "frequency")
    private int frequency;

    public Habit(String name, int frequency, String description, LocalDate endDate) {
        this.name = name;
        this.frequency = frequency;
        this.description = description;
        this.endDate = endDate;
    }

    // gettery i settery

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getFrequency() { return frequency; }
    public void setFrequency(int frequency) { this.frequency = frequency; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

}
