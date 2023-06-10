package com.auebds.coffeui.ui.schedule.create;

import com.auebds.coffeui.entity.Day;

/**
 * This interface is proof I can't make architecturally sound decisions.
 *
 * @author Dimitris Tsirmpas
 */
interface DayManager {
    /**
     * Make the button corresponding to a specific day clickable.
     * @param day the day whose button will be made clickable
     */
    void makeClickable(Day day);

    /**
     * Make the button corresponding to a specific day un-clickable.
     * @param day the day whose button will be made un-clickable
     */
    void makeUnclickable(Day day);

    /**
     * Change the appearance of the button corresponding to a specific day to being selected.
     * @param day the day whose button will be marked as selected
     */
    void markSelected(Day day);

    /**
     * Change the appearance of the button corresponding to a specific day to being un-selected.
     * @param day the day whose button will be marked as un-selected
     */
    void markUnselected(Day day);

}
