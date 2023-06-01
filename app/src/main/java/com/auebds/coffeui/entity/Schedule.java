package com.auebds.coffeui.entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Collection;

/**
 * A record class representing a saved schedule.
 * @author Dimitris Tsirmpas
 */
public class Schedule implements Serializable {

    private final String name;
    private final boolean isRepeatable;
    private final Collection<Day> days;
    private final LocalTime time;
    private final DrinkType type;
    private boolean isActive;

    /**
     * Create a new schedule.
     * @param name the name of the schedule
     * @param isRepeatable whether the schedule must repeat more than once
     * @param days the days on which the schedule will be active
     * @param time the time when the schedule will activate
     * @param drinkType the type of drink that will be dispensed when the schedule is activated
     */
    public Schedule(String name, boolean isRepeatable, Collection<Day> days, LocalTime time,
                    DrinkType drinkType, boolean isActive) {
        this.name = name;
        this.isRepeatable = isRepeatable;
        this.days = days;
        this.time = time;
        this.type = drinkType;
        this.isActive = isActive;
    }

    public String getName() {
        return this.name;
    }

    public boolean isRepeatable() {
        return this.isRepeatable;
    }

    public Collection<Day> getDays() {
        return this.days;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public DrinkType getType() {return this.type;}

    public boolean isActive() {return this.isActive;}

    public void activate() {this.isActive = true;}

    public void disactivate() {this.isActive = false;}
}
