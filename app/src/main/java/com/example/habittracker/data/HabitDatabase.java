package com.example.habittracker.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Habit.class, HabitDay.class}, version = 9, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class HabitDatabase extends RoomDatabase {
    private static HabitDatabase instance;

    public abstract HabitDao habitDao();
    public abstract HabitDayDao habitDayDao();

    public static synchronized HabitDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            HabitDatabase.class, "habit_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
