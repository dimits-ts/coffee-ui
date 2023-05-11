package com.auebds.coffeui.ui.schedule.manage;

import androidx.annotation.NonNull;

import com.auebds.coffeui.entity.Schedule;

import java.util.Collection;

/**
 * The MVP interface for the ManageSchedule Activity.
 *
 * @author Dimitris Tsirmpas
 */
interface ManageScheduleMvp {

    /**
     * The View part of the MVP contract for managing schedules.
     * @author Dimitris Tsirmpas
     */
    interface ManageScheduleView {

        /**
         * Switch to the {@link com.auebds.coffeui.ui.schedule.create.CreateScheduleActivity
         * CreateScheduleActivity}.
         */
        void toCreateScheduleActivity();

        /**
         * Display an error message to the user.
         * @param message the message to be displayed
         */
        void displayError(String message);

        /**
         * Inform the user of a successful deletion.
         * @param deletedName the name of the deleted schedule.
         */
        void displayDeletionSuccess(String deletedName);

        /**
         * Set the activity's schedule fragment to a {@link NoSchedulesFragment}.
         */
        void setUpNoSchedulesFragment();

        /**
         * Set the activity's schedule fragment to a new schedule.
         * @param schedule the schedule to be displayed.
         */
        void switchDisplayedSchedule(@NonNull Schedule schedule);
    }

    /**
     * The Presenter part of the MVP contract for creating schedules.
     * @author Dimitris Tsirmpas
     */
    interface ManageSchedulePresenter {

        /**
         * Get all the saved user schedules.
         * @return a list of all schedules
         */
        Collection<Schedule> getUserSchedules();

        /**
         * Deletes a schedule from persistent storage
         * @param schedule the schedule to be deleted.
         */
        void deleteSchedule(Schedule schedule);

        /**
         * Switch to the {@link com.auebds.coffeui.ui.schedule.create.CreateScheduleActivity
         * CreateScheduleActivity}.
         */
        void toCreateScheduleActivity();

        /**
         * The default method to initializing the activity's schedule fragment.
         * Attempts to display the first schedule, displays a default fragment otherwise.
         */
        void displayFirstSchedule();

        /**
         * Set the activity's schedule fragment to a new schedule.
         * @param schedule the schedule to be display
         */
        void switchDisplayedSchedule(Schedule schedule);
    }
}
