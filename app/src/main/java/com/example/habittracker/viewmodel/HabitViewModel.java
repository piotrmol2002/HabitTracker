package com.example.habittracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.habittracker.data.Habit;
import com.example.habittracker.data.HabitDao;
import com.example.habittracker.data.HabitDatabase;
import com.example.habittracker.data.HabitDay;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class HabitViewModel extends AndroidViewModel {
    private final HabitDao habitDao;
    private final LiveData<List<Habit>> allHabits;

    public HabitViewModel(@NonNull Application application) {
        super(application);
        HabitDatabase db = HabitDatabase.getInstance(application);
        habitDao = db.habitDao();
        allHabits = db.habitDao().getAllHabits();
    }

    public LiveData<List<Habit>> getAllHabits() {
        return allHabits;
    }

    public void markHabitAsComplete(Habit habit, LocalDate date) {
        ArrayList<HabitDay> habitDays = new ArrayList<>(habit.getHabitDays());
        boolean found = false;

        for (HabitDay habitDay : habitDays) {
            if (habitDay.getDate().equals(date)) {
                habitDay.setComplete(true);
                found = true;
                break;
            }
        }

        // Jeśli nie znaleziono daty, dodajemy nową
        if (!found) {
            habitDays.add(new HabitDay(date, true));
        }

        // Zaktualizuj listę dni w Habit
        habit.setHabitDays(habitDays);

        // Aktualizacja w bazie danych
        Executors.newSingleThreadExecutor().execute(() -> habitDao.update(habit));
    }

    public void updateHabit(Habit habit) {
        new Thread(() -> habitDao.update(habit)).start();
    }
}
