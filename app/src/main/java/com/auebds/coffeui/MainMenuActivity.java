package com.auebds.coffeui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

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
    }

    private String capitalize(String str){
        if(str.isEmpty()) // avoid IndexOutOfBounds exception
            return str;
        else
            return str.substring(0, 1).toUpperCase(Locale.ROOT) +
                    str.substring(1).toLowerCase(Locale.ROOT);
    }
}