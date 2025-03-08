package com.example.habittracker.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import java.util.List;

@Dao
public interface HabitDao {
    @Insert
    void insertHabit(Habit habit);

    @Update
    void update(Habit habit);

    @Query("SELECT * FROM habit_table ORDER BY id ASC")
    LiveData<List<Habit>> getAllHabits();

    @Query("SELECT * FROM habit_table WHERE id = :habitId")
    Habit getHabitById(int habitId);

    @Transaction
    @Query("SELECT * FROM habit_table WHERE id = :habitId")
    HabitWithDays getHabitWithDays(int habitId);
}
