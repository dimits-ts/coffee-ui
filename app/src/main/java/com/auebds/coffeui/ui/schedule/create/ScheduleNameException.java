package com.auebds.coffeui.ui.schedule.create;

/**
 * A custom exception used to indicate an already used schedule name.
 * @author Dimitris Tsirmpas
 */
class ScheduleNameException extends Exception {

    public ScheduleNameException() {
        super();
    }

    public ScheduleNameException(String exceptionMessage) {
        super(exceptionMessage);
    }

}
