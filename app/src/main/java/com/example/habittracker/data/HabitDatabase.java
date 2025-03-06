package com.example.habittracker.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Habit.class}, version = 8)
@TypeConverters({DateConverter.class, HabitDayConverter.class})
public abstract class HabitDatabase extends RoomDatabase {
    public abstract HabitDao habitDao();
    private static HabitDatabase instance;

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
