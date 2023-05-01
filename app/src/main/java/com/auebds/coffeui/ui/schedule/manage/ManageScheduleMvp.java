package com.auebds.coffeui.ui.schedule.manage;

import com.auebds.coffeui.entity.Schedule;

import java.util.Collection;
import java.util.List;

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
         * Display a success message to the user.
         * @param message the message to be displayed
         */
        void displaySuccess(String message);
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
    }
}
