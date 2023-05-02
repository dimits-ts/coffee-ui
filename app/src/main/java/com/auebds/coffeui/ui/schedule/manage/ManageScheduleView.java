package com.auebds.coffeui.ui.schedule.manage;

import com.auebds.coffeui.R;
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
    public void displaySuccess(String message) {
        Snackbar.make(this.activity.getRootView(), message, SNACKBAR_DURATION).show();
    }
}
