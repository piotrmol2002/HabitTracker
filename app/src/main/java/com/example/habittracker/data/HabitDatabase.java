package com.example.habittracker.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Habit.class}, version = 3)
public abstract class HabitDatabase extends RoomDatabase {
    private static HabitDatabase instance;

    public abstract HabitDao habitDao();

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
