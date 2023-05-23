package com.auebds.coffeui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.auebds.coffeui.ui.drinks.CreateChocolateActivity;
import com.auebds.coffeui.ui.drinks.CreateEspressoActivity;
import com.auebds.coffeui.ui.drinks.CreateFrenchActivity;
import com.auebds.coffeui.ui.drinks.CreateTeaActivity;
import com.auebds.coffeui.ui.schedule.create.CreateScheduleActivity;
import com.auebds.coffeui.ui.schedule.manage.ManageScheduleActivity;

import java.time.LocalDate;
import java.util.Locale;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        LocalDate today = LocalDate.now();
        String month = capitalize(today.getMonth().toString());
        TextView dateView = findViewById(R.id.textDate);
        dateView.setText(String.format(Locale.ROOT, "%s %d", month, today.getDayOfMonth()));

        Button scheduleButton = findViewById(R.id.schedulesButton);
        scheduleButton.setOnClickListener(view -> {
            // later switch this for schedule management activity
            Intent intent = new Intent(MainMenuActivity.this, ManageScheduleActivity.class);
            startActivity(intent);
        });

        Button chocolateButton = findViewById(R.id.chocolateButton);
        chocolateButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainMenuActivity.this, CreateChocolateActivity.class);
            startActivity(intent);
        });

        Button marineButton = findViewById(R.id.frenchButton);
        marineButton.setOnClickListener(view ->{
            Intent intent = new Intent(MainMenuActivity.this, CreateFrenchActivity.class);
            startActivity(intent);
        });

        Button teaButton = findViewById(R.id.teaButton);
        teaButton.setOnClickListener(view ->{
            Intent intent = new Intent(MainMenuActivity.this, CreateTeaActivity.class);
            startActivity(intent);
        });

        Button espressoButton = findViewById(R.id.espressoButton);
        espressoButton.setOnClickListener(view ->{
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