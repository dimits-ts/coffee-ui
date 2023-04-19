package com.auebds.coffeui.ui.schedule.create;

/**
 * A schedule's repetition period.
 * Auxiliary enum used to automatically toggle days in the UI depending on the user's choice.
 * Only one value can be selected at any one time.
 * @author Dimitris Tsirmpas
 */
enum RepetitionPeriod {
    ONCE, DAILY, WEEKDAY, WEEKEND, CUSTOM
}
