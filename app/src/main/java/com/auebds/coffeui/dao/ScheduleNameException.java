package com.auebds.coffeui.dao;

/**
 * A custom exception used to indicate an already used schedule name.
 * @author Dimitris Tsirmpas
 */
public class ScheduleNameException extends Exception {

    public ScheduleNameException() {
        super();
    }

    public ScheduleNameException(String exceptionMessage) {
        super(exceptionMessage);
    }

}
