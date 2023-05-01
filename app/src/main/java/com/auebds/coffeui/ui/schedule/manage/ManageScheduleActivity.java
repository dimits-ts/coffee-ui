package com.auebds.coffeui.ui.schedule.manage;
import com.auebds.coffeui.MainMenuActivity;
import com.auebds.coffeui.R;
import com.auebds.coffeui.dao.DebugScheduleDao;
import com.auebds.coffeui.entity.Schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

/**
 * The Activity managing already-defined schedules.
 *
 * @author Dimitris Tsirmpas
 */
public class ManageScheduleActivity extends AppCompatActivity {
    private final ManageScheduleMvp.ManageSchedulePresenter presenter;

    public ManageScheduleActivity() {
        this.presenter = new ManageSchedulePresenter(
                new ManageScheduleView(this), new DebugScheduleDao());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_schedule);

        RecyclerView scheduleListView = (RecyclerView) findViewById(R.id.recyclerView);
        scheduleListView.setAdapter(new ScheduleAdapter(presenter.getUserSchedules().toArray(new Schedule[0])));
    }

    void toCreateScheduleActivity() {
        Intent createIntent = new Intent(ManageScheduleActivity.this, MainMenuActivity.class);
        startActivity(createIntent);
    }
}