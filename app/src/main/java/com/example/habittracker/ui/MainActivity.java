package com.example.habittracker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.habittracker.R;
import com.example.habittracker.data.Habit;
import com.example.habittracker.data.HabitDatabase;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private HabitDatabase habitDatabase;
    private RecyclerView recyclerView;
    private HabitAdapter habitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        habitDatabase = HabitDatabase.getInstance(this);
        recyclerView = findViewById(R.id.recycler_view_habits);
        Button buttonAdd = findViewById(R.id.button_add_habit);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddHabitActivity.class);
            startActivity(intent);
        });

        loadHabits();
    }

    private void loadHabits() {
        new Thread(() -> {
            List<Habit> habits = habitDatabase.habitDao().getAllHabits();
            runOnUiThread(() -> {
                habitAdapter = new HabitAdapter(habits);
                recyclerView.setAdapter(habitAdapter);
            });
        }).start();
    }
}
