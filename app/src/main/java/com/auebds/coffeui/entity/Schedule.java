package com.auebds.coffeui.entity;

import java.time.LocalTime;

/**
 * A record class representing a saved schedule.
 * @author Dimitris Tsirmpas
 */
public class Schedule {

    private final String name;
    private final boolean isRepeatable;
    private final Iterable<Day> days;
    private final LocalTime time;

    /**
     * Create a new schedule.
     * @param name the name of the schedule
     * @param isRepeatable whether the schedule must repeat more than once
     * @param days the days on which the schedule will be active
     * @param time the time (+ timezone information) when the schedule will activate
     */
    public Schedule(String name, boolean isRepeatable, Iterable<Day> days, LocalTime time) {
        this.name = name;
        this.isRepeatable = isRepeatable;
        this.days = days;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public boolean isRepeatable() {
        return isRepeatable;
    }

    public Iterable<Day> getDays() {
        return days;
    }

    public LocalTime getTime() {
        return time;
    }
}
