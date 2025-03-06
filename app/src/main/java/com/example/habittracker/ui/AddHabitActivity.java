package com.example.habittracker.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.habittracker.R;
import com.example.habittracker.data.Habit;
import com.example.habittracker.data.HabitDatabase;
import java.util.concurrent.Executors;

public class AddHabitActivity extends AppCompatActivity {
    private EditText editTextHabitName;
    private Spinner spinnerFrequency;
    private static final int[] FREQUENCY_VALUES = {1, 2, 3, 4, 5, 6, 7, 14, 28};
    private EditText editTextHabitDescription;


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
    }

    private void saveHabit() {
        String habitName = editTextHabitName.getText().toString().trim();
        String habitDescription = editTextHabitDescription.getText().toString().trim();

        if (habitName.isEmpty()) {
            Toast.makeText(this, "Wpisz nazwę nawyku!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (habitDescription.isEmpty()) {
            Toast.makeText(this, "Dodaj opis!", Toast.LENGTH_SHORT).show();
            return;
        }

        int selectedFrequency = FREQUENCY_VALUES[spinnerFrequency.getSelectedItemPosition()];
        Habit newHabit = new Habit(habitName, false, selectedFrequency, habitDescription);


        Executors.newSingleThreadExecutor().execute(() -> {
            HabitDatabase.getInstance(this).habitDao().insertHabit(newHabit);
            runOnUiThread(() -> {
                Toast.makeText(this, "Nawyk dodany!", Toast.LENGTH_SHORT).show();
                finish();
            });
        });
    }
}
