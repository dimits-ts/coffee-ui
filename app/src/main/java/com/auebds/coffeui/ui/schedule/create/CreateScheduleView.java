package com.auebds.coffeui.ui.schedule.create;

import android.app.AlertDialog;

import com.auebds.coffeui.R;
import com.auebds.coffeui.entity.Day;
import com.auebds.coffeui.entity.DrinkType;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.time.OffsetTime;

/**
 * The concrete implementation of the schedule creation MVP View.
 *
 * @author Dimitris Tsirmpas
 */
class CreateScheduleView implements  CreateScheduleMvp.CreateScheduleView {
    private final static int SNACKBAR_DURATION = BaseTransientBottomBar.LENGTH_SHORT;
    private final CreateScheduleActivity activity;

    public CreateScheduleView(CreateScheduleActivity activity) {
        this.activity = activity;
    }

    @Override
    public void groupSelected(RepetitionPeriod repetitionPeriod) {

    }

    @Override
    public void daySelected(Day day) {

    }

    @Override
    public void displayNameConflictError(String name) {
        //TODO: placeholder error
        String message = String.format("The name %s is already used for another schedule. " +
                "Please select a different name", name);
        Snackbar.make(activity.getRootView(), message, SNACKBAR_DURATION).show();
    }

    @Override
    public void displayError(String message) {
        //TODO: placeholder error
        Snackbar.make(activity.getRootView(), message, SNACKBAR_DURATION).show();
    }

    @Override
    public void displaySuccess(String message) {
        //TODO: Replace string value
        Snackbar.make(activity.getRootView(), R.string.app_name, SNACKBAR_DURATION).show();
    }

    @Override
    public void toMenu() {

    }

    @Override
    public Iterable<Day> getDays() {
        return null;
    }

    @Override
    public OffsetTime getTime() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }

    @Override
    public DrinkType getSelectedDrink() {
        return null;
    }
}
