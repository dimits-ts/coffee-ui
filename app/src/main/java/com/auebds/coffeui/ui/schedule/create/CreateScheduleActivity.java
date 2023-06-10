package com.auebds.coffeui.ui.schedule.create;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.auebds.coffeui.R;
import com.auebds.coffeui.dao.DebugScheduleDao;
import com.auebds.coffeui.dao.SettingsDao;
import com.auebds.coffeui.databinding.ActivityCreateScheduleBinding;
import com.auebds.coffeui.ui.schedule.manage.ManageScheduleActivity;
import com.auebds.coffeui.util.SingletonTTS;

import java.util.ArrayList;

/**
 * The Activity that manages the creation of a schedule.
 *
 * @author Dimitris Tsirmpas
 */
public class CreateScheduleActivity extends AppCompatActivity {
    private final CreateScheduleMvp.CreateSchedulePresenter presenter =
            new CreateSchedulePresenter(new CreateScheduleView(this),
                    DebugScheduleDao.getInstance());

    private ArrayList<SwitchableFragment> fragmentList;
    private ActivityCreateScheduleBinding binding;

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateScheduleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentList = new ArrayList<>();
        fragmentList.add(ScheduleNameFragment.newInstance(presenter));
        fragmentList.add(ScheduleDrinkFragment.newInstance(presenter));
        fragmentList.add(TimePickerFragment.newInstance(presenter));
        fragmentList.add(ScheduleDayFragment.newInstance(presenter));


        SingletonTTS.getInstance(getApplicationContext(),
                SettingsDao.getInstance(getApplicationContext()))
                .speakOnce(getString(R.string.tts_create_schedules));
    }


    View getRootView() {
        return getWindow().getDecorView().getRootView();
    }

    void toMenu() {
        this.finish();
    }

    void toMenuWithMessage(String message) {
        Intent menuIntent = new Intent();
        menuIntent.putExtra(ManageScheduleActivity.ARG_MESSAGE, message);
        this.setResult(RESULT_OK, menuIntent);
        this.finish();
    }

    String getStringRes(@StringRes int stringId, String... args) {
        return getString(stringId, (Object []) args);
    }

    private void switchFragments(Fragment fragment) {

    }

}