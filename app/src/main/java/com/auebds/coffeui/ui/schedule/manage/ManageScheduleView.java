package com.auebds.coffeui.ui.schedule.manage;

/**
 * The concrete implementation of the View for the ManageSchedule MVP.
 *
 * @author Dimitris Tsirmpas
 */
class ManageScheduleView implements ManageScheduleMvp.ManageScheduleView {
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

    }

    @Override
    public void displaySuccess(String message) {

    }
}
