package com.example.habittracker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.habittracker.R;
import com.example.habittracker.data.HabitDatabase;
import com.example.habittracker.viewmodel.HabitViewModel;
import com.jakewharton.threetenabp.AndroidThreeTen;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private HabitViewModel habitViewModel;
    private HabitDatabase habitDatabase;
    private RecyclerView recyclerView;
    private HabitAdapter habitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidThreeTen.init(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicjalizacja bazy danych
        habitDatabase = HabitDatabase.getInstance(getApplicationContext());

        recyclerView = findViewById(R.id.recycler_view_habits);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        habitAdapter = new HabitAdapter(new ArrayList<>(), habitViewModel);
        recyclerView.setAdapter(habitAdapter);

        habitViewModel = new ViewModelProvider(this).get(HabitViewModel.class);
        habitViewModel.getAllHabits().observe(this, habits -> {
            habitAdapter.setHabits(habits);  // Aktualizacja RecyclerView
        });

        Button buttonAdd = findViewById(R.id.button_add_habit);
        buttonAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddHabitActivity.class);
            startActivity(intent);
        });
    }
}

