package com.example.habittracker.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.habittracker.R;
import com.example.habittracker.data.Habit;
import com.example.habittracker.data.HabitDatabase;
import java.util.concurrent.Executors;

public class AddHabitActivity extends AppCompatActivity {
    private EditText editTextHabitName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        editTextHabitName = findViewById(R.id.edit_text_habit_name);
        Button buttonSaveHabit = findViewById(R.id.button_save_habit);

        buttonSaveHabit.setOnClickListener(v -> saveHabit());
    }

    private void saveHabit() {
        String habitName = editTextHabitName.getText().toString().trim();

        if (habitName.isEmpty()) {
            Toast.makeText(this, "Wpisz nazwę nawyku!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Ustawienie domyślnego harmonogramu ("codziennie") i statusu (nieukończony)
        Habit newHabit = new Habit(habitName, "codziennie", false);

        Executors.newSingleThreadExecutor().execute(() -> {
            HabitDatabase.getInstance(this).habitDao().insertHabit(newHabit);
            runOnUiThread(() -> {
                Toast.makeText(this, "Nawyk dodany!", Toast.LENGTH_SHORT).show();
                finish();
            });
        });
    }
}
