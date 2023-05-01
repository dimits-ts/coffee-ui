package com.auebds.coffeui.ui.schedule.create;

import android.util.Log;

import androidx.annotation.NonNull;

import com.auebds.coffeui.R;
import com.auebds.coffeui.entity.Day;
import com.auebds.coffeui.entity.DrinkType;
import com.auebds.coffeui.entity.Schedule;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * The concrete implementation of the schedule creation MVP View.
 *
 * @author Dimitris Tsirmpas
 */
class CreateScheduleView implements  CreateScheduleMvp.CreateScheduleView {
    private final static int SNACKBAR_DURATION = BaseTransientBottomBar.LENGTH_SHORT;
    private final CreateScheduleActivity activity;
    private final HashMap<Day, Boolean> selectedDaysMap;
    private boolean isRepeatable;

    public CreateScheduleView(CreateScheduleActivity activity) {
        this.activity = activity;

        this.selectedDaysMap = new HashMap<>();
        for(Day day: Day.values()) {
            this.selectedDaysMap.put(day, false);
        }
    }

    @Override
    public void groupSelected(@NonNull RepetitionPeriod repetitionPeriod) {
        switch(repetitionPeriod) {
            case ONCE:
            case CUSTOM:
                allClickable();
                break;
            case DAILY:
                markAllSelected();
                allUnclickable();
                break;
            case WEEKDAY:
                markAllSelected();
                markUnselected(Day.SATURDAY);
                markUnselected(Day.SUNDAY);
                allUnclickable();
                break;
            case WEEKEND:
                markAllUnselected();
                markSelected(Day.SATURDAY);
                markSelected(Day.SUNDAY);
                allUnclickable();
                break;
        }

        this.isRepeatable = repetitionPeriod != RepetitionPeriod.ONCE;
    }

    @Override
    public void daySelected(Day day) {
        if(Boolean.TRUE.equals(this.selectedDaysMap.get(day))) {
            markUnselected(day);
        } else {
            markSelected(day);
        }
    }

    @Override
    public void displayNameConflictError(String name) {
        String message = this.activity.getStringRes(R.string.schedule_name_error_message, name);
        Snackbar.make(this.activity.getRootView(), message, SNACKBAR_DURATION).show();
    }

    @Override
    public void displayError(String message) {
        String completeMessage = this.activity.getStringRes(R.string.schedule_error_message, message);
        Snackbar.make(this.activity.getRootView(), completeMessage, SNACKBAR_DURATION).show();
    }

    @Override
    public void displaySuccess(Schedule schedule) {
        String message = this.activity.getStringRes(R.string.schedule_success_message, schedule.getName());
        Snackbar.make(this.activity.getRootView(), message, SNACKBAR_DURATION).show();
    }

    @Override
    public void toMenu() {
        this.activity.toMenu();
    }

    @Override
    public Collection<Day> getDays() {
        LinkedList<Day> ls = new LinkedList<>();
        for(Map.Entry<Day, Boolean> entry: this.selectedDaysMap.entrySet()){
            if(entry.getValue()) {
                ls.add(entry.getKey());
            }
        }
        return ls;
    }

    @Override
    public LocalTime getTime() {
        return this.activity.getTime();
    }

    @Override
    public String getName() {
        return this.activity.getName();
    }

    @Override
    public boolean isRepeatable() {
        return this.isRepeatable;
    }

    @Override
    public DrinkType getSelectedDrink() {
        try {
            return activity.getSelectedDrink();
        } catch (IllegalArgumentException iae) {
            Log.e("CREATE_SCHEDULE", String.valueOf(iae));
            return null;
        }
    }

    private void markSelected(Day day) {
        this.activity.markSelected(day);
        this.selectedDaysMap.put(day, true);
    }

    private void markUnselected(Day day) {
        this.activity.markUnselected(day);
        this.selectedDaysMap.put(day, false);
    }

    private void allClickable() {
        for(Day day: Day.values()){
            this.activity.makeClickable(day);
        }
    }

    private void allUnclickable() {
        for(Day day: Day.values()){
            this.activity.makeUnclickable(day);
        }
    }

    private void markAllSelected() {
        for(Day day: Day.values()){
            markSelected(day);
        }
    }

    private void markAllUnselected() {
        for(Day day: Day.values()){
            markUnselected(day);
        }
    }

}
