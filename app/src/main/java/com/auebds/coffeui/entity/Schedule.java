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
    private final DrinkType type;

    /**
     * Create a new schedule.
     * @param name the name of the schedule
     * @param isRepeatable whether the schedule must repeat more than once
     * @param days the days on which the schedule will be active
     * @param time the time when the schedule will activate
     * @param drinkType the type of drink that will be dispensed when the schedule is activated
     */
    public Schedule(String name, boolean isRepeatable, Iterable<Day> days, LocalTime time,
                    DrinkType drinkType) {
        this.name = name;
        this.isRepeatable = isRepeatable;
        this.days = days;
        this.time = time;
        this.type = drinkType;
    }

    public String getName() {
        return this.name;
    }

    public boolean isRepeatable() {
        return this.isRepeatable;
    }

    public Iterable<Day> getDays() {
        return this.days;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public DrinkType getType() {return this.type;}
}
