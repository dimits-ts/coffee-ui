package com.auebds.coffeui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.auebds.coffeui.databinding.ActivityMainMenuBinding;
import com.auebds.coffeui.ui.drinks.chocolate.CreateChocolateActivity;
import com.auebds.coffeui.ui.drinks.espresso.CreateEspressoActivity;
import com.auebds.coffeui.ui.drinks.french.CreateFrenchActivity;
import com.auebds.coffeui.ui.drinks.tea.CreateTeaActivity;
import com.auebds.coffeui.ui.schedule.manage.ManageScheduleActivity;

import java.time.LocalDate;
import java.util.Locale;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainMenuBinding binding = ActivityMainMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LocalDate today = LocalDate.now();
        String month = capitalize(today.getMonth().toString());
        binding.textDate.setText(String.format(Locale.ROOT, "%s %d", month, today.getDayOfMonth()));

        binding.schedulesButton.setOnClickListener(view -> {
            // later switch this for schedule management activity
            Intent intent = new Intent(MainMenuActivity.this, ManageScheduleActivity.class);
            startActivity(intent);
        });

        binding.chocolateButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainMenuActivity.this, CreateChocolateActivity.class);
            startActivity(intent);
        });

        binding.frenchButton.setOnClickListener(view ->{
            Intent intent = new Intent(MainMenuActivity.this, CreateFrenchActivity.class);
            startActivity(intent);
        });

        binding.teaButton.setOnClickListener(view ->{
            Intent intent = new Intent(MainMenuActivity.this, CreateTeaActivity.class);
            startActivity(intent);
        });

        binding.espressoButton.setOnClickListener(view ->{
            Intent intent = new Intent(MainMenuActivity.this, CreateEspressoActivity.class);
            startActivity(intent);
        });
    }

    private String capitalize(String str){
        if(str.isEmpty()) // avoid IndexOutOfBounds exception
            return str;
        else
            return str.substring(0, 1).toUpperCase(Locale.ROOT) +
                    str.substring(1).toLowerCase(Locale.ROOT);
    }
}