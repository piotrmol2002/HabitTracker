package com.example.habittracker.data;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import org.threeten.bp.LocalDate;
import java.util.List;

@Dao
public interface HabitDayDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<HabitDay> habitDays);

    @Query("SELECT * FROM habit_days WHERE habitId = :habitId")
    List<HabitDay> getHabitDays(int habitId);

    @Update
    void updateHabitDay(HabitDay habitDay);

    @Query("UPDATE habit_days SET isComplete = :isComplete WHERE habitId = :habitId AND date = :date")
    void updateCompletionStatus(int habitId, LocalDate date, boolean isComplete);
}
