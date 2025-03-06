package com.example.habittracker.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
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
}
