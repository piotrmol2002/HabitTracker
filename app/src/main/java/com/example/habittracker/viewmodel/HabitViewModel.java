package com.example.habittracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.habittracker.data.Habit;
import com.example.habittracker.data.HabitDatabase;

import java.util.List;

public class HabitViewModel extends AndroidViewModel {
    private final LiveData<List<Habit>> allHabits;

    public HabitViewModel(@NonNull Application application) {
        super(application);
        HabitDatabase db = HabitDatabase.getInstance(application);
        allHabits = db.habitDao().getAllHabits();
    }

    public LiveData<List<Habit>> getAllHabits() {
        return allHabits;
    }
}
