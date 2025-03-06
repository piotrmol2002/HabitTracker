package com.example.habittracker.data;

import androidx.room.TypeConverter;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class HabitDayConverter {

    // Konwerter na String
    @TypeConverter
    public static String fromHabitDayList(ArrayList<HabitDay> habitDays) {
        if (habitDays == null) {
            return null;
        }
        Gson gson = new Gson();
        return gson.toJson(habitDays);
    }

    // Konwerter z String do ArrayList<HabitDay>
    @TypeConverter
    public static ArrayList<HabitDay> toHabitDayList(String habitDaysString) {
        if (habitDaysString == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<HabitDay>>() {}.getType();
        return gson.fromJson(habitDaysString, type);
    }
}
