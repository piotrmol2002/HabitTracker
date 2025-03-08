package com.example.habittracker.data;
import androidx.room.Embedded;
import androidx.room.Relation;
import java.util.List;

public class HabitWithDays {
    @Embedded
    public Habit habit;

    @Relation(
            parentColumn = "id",
            entityColumn = "habitId"
    )
    public List<HabitDay> habitDays;
}
