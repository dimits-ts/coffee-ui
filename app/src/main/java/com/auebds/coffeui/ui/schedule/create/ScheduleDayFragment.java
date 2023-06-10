package com.auebds.coffeui.ui.schedule.create;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.auebds.coffeui.R;
import com.auebds.coffeui.databinding.FragmentScheduleDayBinding;
import com.auebds.coffeui.entity.Day;

import java.util.HashMap;
import java.util.Map;

/**
 * A fragment managing the days a schedule will be active in.
 *
 * @author Dimits Tsirmpas
 */
public class ScheduleDayFragment extends Fragment implements Switchable, DayManager {

    private static final String ARG_PRESENTER = "PRESENTER";

    private FragmentScheduleDayBinding binding;
    private CreateScheduleMvp.CreateSchedulePresenter presenter;
    private Map<Day, Button> dayButtonHashMap;


    public ScheduleDayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param presenter the MVP presenter
     * @return A new instance of fragment ScheduleNameFragment.
     */
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

        this.dayButtonHashMap = this.createDayButtonMap();
        this.attachRadioButtonListeners();
        this.assignDayButtonListeners();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentScheduleDayBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    @Override
    public void onSwitch() {
        // all logic is already handled by click listeners
    }

    @Override
    public void makeClickable(Day day) {
        Button button = this.dayButtonHashMap.get(day);
        assert button != null;
        button.setEnabled(true);
    }


    @Override
    public void makeUnclickable(Day day) {
        Button button = this.dayButtonHashMap.get(day);
        assert button != null;
        button.setEnabled(false);
    }

    @Override
    public void markSelected(Day day) {
        Button button = this.dayButtonHashMap.get(day);
        assert button != null;
        button.setBackgroundColor(ContextCompat.getColor(requireContext(),
                R.color.button_selected));
    }

    @Override
    public void markUnselected(Day day) {
        Button button = this.dayButtonHashMap.get(day);
        assert button != null;
        button.setBackgroundColor(ContextCompat.getColor(requireContext(),
                R.color.primary_grey));
    }

    @SuppressLint("ClickableViewAccessibility")
    private void assignDayButtonListeners() {
        for(Map.Entry<Day, Button> entry: this.dayButtonHashMap.entrySet()){
            entry.getValue().setOnTouchListener((view, event) -> {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    //view.performClick();
                    this.presenter.daySelected(entry.getKey());
                }
                return false; // don't need this event for anything else
            });
        }
    }


    /**
     * Notify the presenter if any of the choices are selected.
     */
    private void attachRadioButtonListeners() {
        binding.buttonRepeatOnce.setOnClickListener(view ->
                presenter.groupSelected(RepetitionPeriod.ONCE));
        binding.buttonRepeatDaily.setOnClickListener(view ->
                presenter.groupSelected(RepetitionPeriod.DAILY));
        binding.buttonRepeatWeekday.setOnClickListener(view ->
                presenter.groupSelected(RepetitionPeriod.WEEKDAY));
        binding.buttonRepeatWeekend.setOnClickListener(view ->
                presenter.groupSelected(RepetitionPeriod.WEEKEND));
        binding.buttonRepeatCustom.setOnClickListener(view ->
                presenter.groupSelected(RepetitionPeriod.CUSTOM));
    }

    /**
     * Create the internal map mapping days to their toggle buttons.
     * @return the filled map
     */
    private Map<Day, Button> createDayButtonMap() {
        HashMap<Day, Button> map = new HashMap<>();

        map.put(Day.MONDAY, binding.buttonMonday);
        map.put(Day.TUESDAY, binding.buttonTuesday);
        map.put(Day.WEDNESDAY, binding.buttonWendensday);
        map.put(Day.THURSDAY, binding.buttonThursday);
        map.put(Day.FRIDAY, binding.buttonFriday);
        map.put(Day.SATURDAY, binding.buttonSaturday);
        map.put(Day.SUNDAY, binding.buttonSunday);

        // check if accessed before initialization
        assert map.get(Day.MONDAY) != null;

        return map;
    }

}