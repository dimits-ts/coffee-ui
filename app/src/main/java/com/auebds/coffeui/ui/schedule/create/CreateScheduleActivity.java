package com.auebds.coffeui.ui.schedule.create;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.auebds.coffeui.R;
import com.auebds.coffeui.dao.DebugScheduleDao;
import com.auebds.coffeui.entity.Day;

import java.util.HashMap;

public class CreateScheduleActivity extends AppCompatActivity {

    private final CreateScheduleMvp.CreateSchedulePresenter presenter =
            new CreateSchedulePresenter(new CreateScheduleView(this), new DebugScheduleDao());

    private HashMap<Day, Button> dayButtonHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule);

        this.dayButtonHashMap = createDayButtonMap();
    }

    View getRootView() {
        return getWindow().getDecorView().getRootView();
    }

    private HashMap<Day, Button> createDayButtonMap() {
        return null;
    }

}