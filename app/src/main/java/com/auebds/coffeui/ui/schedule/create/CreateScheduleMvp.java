package com.auebds.coffeui.ui.schedule.create;

import com.auebds.coffeui.entity.Day;
import com.auebds.coffeui.entity.DrinkType;

import java.time.OffsetTime;

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
         * Close the create schedule screen and go back to the main menu.
         */
        void toMenu();

        /**
         * Get the selected schedule days from the UI.
         * @return an iterable containing the selected days
         */
        Iterable<Day> getDays();

        /**
         * Get the time (hour, minutes) when the schedule is scheduled to start.
         * @return the time (with timezone information)
         */
        OffsetTime getTime();

        /**
         * Get the user-defined name of the schedule.
         * @return the name of the schedule
         */
        String getName();

        /**
         * Get whether or not the schedule must be repeated more than once.
         * @return true if the schedule is repeatable
         */
        boolean isRepeatable();

        /**
         * Get the schedule's selected drink type.
         * @return the selected drink type
         */
        DrinkType getSelectedDrink();
    }

    /**
     * The Presenter part of the MVP contract for creating schedules.
     * @author Dimitris Tsirmpas
     */
    interface CreateSchedulePresenter {

        /**
         * Save the current schedule.
         * @throws ScheduleNameException in the case of a name conflict
         * @throws Exception if an error occurs while saving to permanent storage
         */
        void save() throws ScheduleNameException, Exception;

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
    }
}
