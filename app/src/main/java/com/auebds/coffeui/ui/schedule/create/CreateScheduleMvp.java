package com.auebds.coffeui.ui.schedule.create;

import com.auebds.coffeui.entity.Day;
import com.auebds.coffeui.entity.DrinkType;
import com.auebds.coffeui.entity.Schedule;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Collection;

/**
 * The MVP contract between the view and presenter for creating schedules.
 * @author Dimitris Tsirmpas
 */
interface CreateScheduleMvp {

    /**
     * The View part of the MVP contract for creating schedules.
     * @author Dimitris Tsirmpas
     */
    interface CreateScheduleView {

        /**
         * Make the UI react to a repetition group being selected.
         * @param repetitionPeriod the selected repetition group
         * @see RepetitionPeriod
         */
        void groupSelected(RepetitionPeriod repetitionPeriod);

        /**
         * Notify the UI that a day has been selected.
         * @param day the selected day
         */
        void daySelected(Day day);

        /**
         * Display an error message to the user in case of a name conflict.
         * @param name the name that caused the conflict
         */
        void displayNameConflictError(String name);

        /**
         * Display an error message to the user.
         * @param message the message to be displayed
         */
        void displayError(String message);

        /**
         * Display a success message to the user.
         * @param schedule the created schedule
         */
        void displaySuccess(Schedule schedule);

        /**
         * Close the create schedule screen and go back to the main menu.
         */
        void toMenu();

        /**
         * Get whether or not the schedule must be repeated more than once.
         * @return true if the schedule is repeatable
         */
        boolean isRepeatable();

        /**
         * Get the days that have been selected.
         * @return a collection of all selected days.
         */
        Collection<Day> getDays();
    }

    /**
     * The Presenter part of the MVP contract for creating schedules.
     * @author Dimitris Tsirmpas
     */
    interface CreateSchedulePresenter extends Serializable {

        /**
         * Save the current schedule.
         */
        void save();

        /**
         * Notify the presenter that a repetition group was selected.
         * @param repetitionPeriod the selected repetition group
         * @see RepetitionPeriod
         */
        void groupSelected(RepetitionPeriod repetitionPeriod);

        /**
         * Notify the presenter that a day has been selected.
         * @param day the selected day
         */
        void daySelected(Day day);

        /**
         * Notify the presenter to change the schedule's name.
         * @param name the selected name.
         */
        void setName(String name);

        /**
         * Notify the presenter to change the schedule's drink type.
         * @param type the selected drink type.
         */
        void setDrinkType(DrinkType type);

        /**
         * Notify the presenter to change the schedule's time.
         * @param time the selected time.
         */
        void setTime(LocalTime time);
    }
}
