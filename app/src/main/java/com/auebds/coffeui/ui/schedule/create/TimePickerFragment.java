package com.auebds.coffeui.ui.schedule.create;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.auebds.coffeui.databinding.FragmentTimePickerBinding;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimePickerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimePickerFragment extends SwitchableFragment {

    private static final String ARG_PRESENTER = "PRESENTER";
    private static final int TIME_PICKER_MIN_INTERVAL = 5;


    private FragmentTimePickerBinding binding;
    private CreateScheduleMvp.CreateSchedulePresenter presenter;

    public TimePickerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param presenter the MVP presenter
     * @return A new instance of fragment ScheduleNameFragment.
     */
    @NonNull
    public static TimePickerFragment newInstance(CreateScheduleMvp.CreateSchedulePresenter presenter) {
        TimePickerFragment fragment = new TimePickerFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PRESENTER, presenter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.presenter = (CreateScheduleMvp.CreateSchedulePresenter) getArguments().getSerializable(ARG_PRESENTER);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTimePickerBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // set time picker interval
        try {
            NumberPicker minutePicker = binding.timePicker.findViewById(Resources.getSystem().getIdentifier(
                    "minute", "id", "android"));
            minutePicker.setMinValue(0);
            minutePicker.setMaxValue((60 / TIME_PICKER_MIN_INTERVAL) - 1);
            List<String> displayedValues = new ArrayList<>();
            for (int i = 0; i < 60; i += TIME_PICKER_MIN_INTERVAL) {
                displayedValues.add(String.format(Locale.getDefault(), "%02d", i));
            }
            minutePicker.setDisplayedValues(displayedValues.toArray(new String[0]));
        } catch (Exception e) {
            Log.e("CREATE_SCHEDULE", e.getLocalizedMessage(), e);
        }
    }

    @Override
    public void onSwitch() {
        if(binding.timePicker.validateInput()){
            LocalTime time = LocalTime.of(binding.timePicker.getHour(), binding.timePicker.getMinute());
            this.presenter.setTime(time);
        } else {
            Log.e("MANAGE_SCHEDULES", "Invalid time.");
        }
    }
}