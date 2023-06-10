package com.auebds.coffeui.ui.schedule.create;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.auebds.coffeui.databinding.FragmentTimePickerBinding;

import java.time.LocalTime;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimePickerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimePickerFragment extends Fragment implements Switchable {

    private static final String ARG_PRESENTER = "PRESENTER";

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
    public static ScheduleNameFragment newInstance(CreateScheduleMvp.CreateSchedulePresenter presenter) {
        ScheduleNameFragment fragment = new ScheduleNameFragment();
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
    public void onSwitch() {
        if(binding.timePicker.validateInput()){
            LocalTime time = LocalTime.of(binding.timePicker.getHour(), binding.timePicker.getMinute());
            this.presenter.setTime(time);
        } else {
            Log.e("MANAGE_SCHEDULES", "Invalid time.");
        }
    }
}