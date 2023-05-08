package com.auebds.coffeui.ui.schedule.manage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.auebds.coffeui.MainMenuActivity;
import com.auebds.coffeui.R;
import com.auebds.coffeui.dao.DebugScheduleDao;

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
        this.setContentView(R.layout.activity_manage_schedule);

        RecyclerView scheduleListView = findViewById(R.id.recyclerView);
        scheduleListView.setAdapter(new ScheduleAdapter(
                presenter,
                ContextCompat.getColor(getBaseContext(), R.color.button_selected),
                ContextCompat.getColor(getBaseContext(), R.color.primary_grey)));

        this.presenter.initializeDisplaySchedule();
    }

    void toCreateScheduleActivity() {
        Intent createIntent = new Intent(ManageScheduleActivity.this, MainMenuActivity.class);
        startActivity(createIntent);
    }

    String getStringRes(@StringRes int stringId, String... args) {
        return getString(stringId, (Object []) args);
    }

    View getRootView() {
        return getWindow().getDecorView().getRootView();
    }
}