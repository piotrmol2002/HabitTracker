package com.example.habittracker.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.habittracker.R;
import com.example.habittracker.data.Habit;
import com.example.habittracker.data.HabitDao;
import com.example.habittracker.data.HabitDatabase;
import com.example.habittracker.data.HabitDay;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class AddHabitActivity extends AppCompatActivity {
    private EditText editTextHabitName;
    private Spinner spinnerFrequency;
    private static final int[] FREQUENCY_VALUES = {1, 2, 3, 4, 5, 6, 7, 14, 28};
    private EditText editTextHabitDescription;
    private LocalDate selectedEndDate;
    private TextView textViewEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_habit);

        editTextHabitName = findViewById(R.id.edit_text_habit_name);
        editTextHabitDescription = findViewById(R.id.edit_text_habit_description);

        spinnerFrequency = findViewById(R.id.spinner_frequency);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"1 dzień", "2 dni", "3 dni", "4 dni", "5 dni", "6 dni", "Tydzień", "2 tygodnie", "4 tygodnie"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrequency.setAdapter(adapter);

        Button buttonSaveHabit = findViewById(R.id.button_save_habit);
        buttonSaveHabit.setOnClickListener(v -> saveHabit());

        textViewEndDate = findViewById(R.id.text_view_end_date);
        Button buttonPickDate = findViewById(R.id.button_pick_end_date);
        buttonPickDate.setOnClickListener(v -> showDatePickerDialog());
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    selectedEndDate = org.threeten.bp.LocalDate.of(year, month + 1, dayOfMonth);
                    textViewEndDate.setText(selectedEndDate.toString());
                },
                org.threeten.bp.LocalDate.now().getYear(),
                org.threeten.bp.LocalDate.now().getMonthValue() - 1,
                org.threeten.bp.LocalDate.now().getDayOfMonth()
        );
        datePickerDialog.show();
    }


    private void saveHabit() {
        String habitName = editTextHabitName.getText().toString().trim();
        String habitDescription = editTextHabitDescription.getText().toString().trim();
        org.threeten.bp.LocalDate endDate = selectedEndDate;

        if (habitName.isEmpty()) {
            Toast.makeText(this, "Wpisz nazwę nawyku!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (habitDescription.isEmpty()) {
            Toast.makeText(this, "Dodaj opis!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (endDate == null) {
            Toast.makeText(this, "Wybierz datę zakończenia!", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedFrequency = FREQUENCY_VALUES[spinnerFrequency.getSelectedItemPosition()];

        Habit newHabit = new Habit(habitName, selectedFrequency, habitDescription, endDate);

        HabitDatabase db = HabitDatabase.getInstance(this);
        HabitDao habitDao = db.habitDao();

        // zapis w bazie danych
        Executors.newSingleThreadExecutor().execute(() -> {

            //generowanie listy w osobnym watku
            long habitId = habitDao.insertHabit(newHabit);
            List<HabitDay> habitDays = generateHabitDays((int) habitId, LocalDate.now(), endDate, selectedFrequency);

            //zapisuje tylko dni nawykow w bazie - nawyki zapisane wyzej
            HabitDatabase.getInstance(this).habitDayDao().insertHabitDays(habitDays);

            runOnUiThread(() -> {
                Toast.makeText(this, "Nawyk dodany!", Toast.LENGTH_SHORT).show();
                finish();
            });
        });
    }

    private List<HabitDay> generateHabitDays(int habitId, LocalDate startDate, LocalDate endDate, int frequency) {
        List<HabitDay> habitDays = new ArrayList<>();
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            habitDays.add(new HabitDay(habitId, currentDate, false)); // Domyślnie isComplete = false
            currentDate = currentDate.plusDays(frequency);
        }
        return habitDays;
    }

}
