package com.auebds.coffeui.ui.schedule.manage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.auebds.coffeui.R;
import com.auebds.coffeui.dao.DebugScheduleDao;
import com.auebds.coffeui.dao.SettingsDao;
import com.auebds.coffeui.databinding.ActivityManageScheduleBinding;
import com.auebds.coffeui.entity.Schedule;
import com.auebds.coffeui.ui.drinks.tutorial.TutorialActivity;
import com.auebds.coffeui.ui.schedule.create.CreateScheduleActivity;
import com.auebds.coffeui.util.SingletonTTS;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

/**
 * The Activity managing already-defined schedules.
 *
 * @author Dimitris Tsirmpas
 */
public class ManageScheduleActivity extends AppCompatActivity {
    public static final String ARG_MESSAGE = "MESSAGE";
    private static final int SNACKBAR_DURATION = BaseTransientBottomBar.LENGTH_SHORT;

    private final ManageScheduleMvp.ManageSchedulePresenter presenter;

    private ActivityManageScheduleBinding binding;
    private ActivityResultLauncher<Void> resultLauncher;
    private ScheduleAdapter adapter;

    public ManageScheduleActivity() {
        this.presenter = new ManageSchedulePresenter(
                new ManageScheduleView(this), DebugScheduleDao.getInstance());
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityManageScheduleBinding.inflate(getLayoutInflater());
        this.setContentView(binding.getRoot());

        binding.listNewScheduleButton.setOnClickListener(v -> this.toCreateScheduleActivity());

        this.adapter = new ScheduleAdapter(
                                presenter,
                                ContextCompat.getColor(getBaseContext(), R.color.button_selected),
                                ContextCompat.getColor(getBaseContext(), R.color.primary_grey));
        binding.recyclerView.setAdapter(this.adapter);

        binding.buttonBack.setOnClickListener(view -> toMenuActivity());

        this.presenter.displayFirstSchedule();

        SingletonTTS tts = SingletonTTS.getInstance(getApplicationContext(),
                        SettingsDao.getInstance(getApplicationContext()));
        tts.speakOnce(getString(R.string.tts_manage_schedules));

        ImageButton helpButton = binding.helpButton;
        helpButton.setOnClickListener(view -> {
            Intent intent = new Intent(ManageScheduleActivity.this, TutorialActivity.class);
            Bundle b = new Bundle();
            b.putString("path", "android.resource://" + getPackageName() + "/" + R.raw.tutorial_manage_schedules);
            intent.putExtras(b);
            startActivity(intent);
            finish();
        });

        // set up new schedule listener
        ActivityResultContract<Void, String> contract = new ActivityResultContract<Void, String>() {
            @NonNull
            @Override
            public Intent createIntent(@NonNull Context context, Void unused) {
                return new Intent(ManageScheduleActivity.this, CreateScheduleActivity.class);
            }

            @Override
            public String parseResult(int resultCode, @Nullable Intent result) {
                if (resultCode != Activity.RESULT_OK || result == null) {
                    return null;
                }
                return result.getStringExtra(ARG_MESSAGE);
            }
        };

        ActivityResultCallback<String> callback = message -> {
            if(message != null) {
                Snackbar.make(this.getRootView(), message, SNACKBAR_DURATION).show();
                tts.speakSentence(message);
            }
        };

        this.resultLauncher = registerForActivityResult(contract, callback);

        binding.listNewScheduleButton.setOnClickListener(view -> toCreateScheduleActivity());


    }

    @Override
    public void onRestart(){
        super.onRestart();
        // only way I found to reliably reload the list
        this.recreate();
    }

    void toCreateScheduleActivity() {
        this.resultLauncher.launch(null);
    }

    void toMenuActivity() {
        this.finish();
    }

    String getStringRes(@StringRes int stringId, String... args) {
        return getString(stringId, (Object []) args);
    }

    View getRootView() {
        return getWindow().getDecorView().getRootView();
    }

    void deleteSchedule(Schedule schedule) {
        this.presenter.deleteSchedule(schedule);
        // set first element as new selected
        this.adapter.updateSchedules(
                (ScheduleViewHolder) binding.recyclerView.findViewHolderForAdapterPosition(0));
    }
}