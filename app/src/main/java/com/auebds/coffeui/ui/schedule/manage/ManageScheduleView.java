package com.auebds.coffeui.ui.schedule.manage;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.auebds.coffeui.R;
import com.auebds.coffeui.entity.Schedule;
import com.google.android.material.snackbar.Snackbar;

/**
 * The concrete implementation of the View for the ManageSchedule MVP contract.
 *
 * @author Dimitris Tsirmpas
 */
class ManageScheduleView implements ManageScheduleMvp.ManageScheduleView {
    private static final int SNACKBAR_DURATION = 2000;
    private final ManageScheduleActivity activity;

    public ManageScheduleView(ManageScheduleActivity activity) {
        this.activity = activity;
    }

    @Override
    public void toCreateScheduleActivity() {
        this.activity.toCreateScheduleActivity();
    }

    @Override
    public void displayError(String message) {
        String completeMessage = this.activity.getStringRes(R.string.schedule_error_message, message);
        Snackbar.make(this.activity.getRootView(), completeMessage, SNACKBAR_DURATION).show();
    }

    @Override
    public void displayDeletionSuccess(String deletedName) {
        String message = this.activity.getStringRes(R.string.schedule_delete_success_message, deletedName);
        Snackbar.make(this.activity.getRootView(), message, SNACKBAR_DURATION).show();
    }

    @Override
    public void setUpNoSchedulesFragment() {
        Fragment newFragment = NoSchedulesFragment.newInstance();
        FragmentTransaction transaction = this.activity.getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.scheduleFragmentContainer, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void switchDisplayedSchedule(@NonNull Schedule schedule) {
        Fragment newFragment = ScheduleFragment.newInstance(schedule);
        FragmentTransaction transaction = this.activity.getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.scheduleFragmentContainer, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
