package com.auebds.coffeui.dao;
import com.auebds.coffeui.entity.Schedule;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * A default implementation of the schedule DAO to be used until replaced by an
 * actual concrete implementation.
 *
 * @author Dimitris Tsirmpas
 */
public class DebugScheduleDao implements ScheduleDao {

    private final Map<String, Schedule> schedules = new HashMap<>();

    @Override
    public void save(Schedule schedule) throws ScheduleNameException {
        if(schedules.containsKey(schedule.getName())) {
            throw new ScheduleNameException(schedule.getName());
        } else {
            schedules.put(schedule.getName(), schedule);
        }

    }

    @Override
    public void delete(Schedule schedule) throws Exception {
        if(schedules.containsKey(schedule.getName())) {
            schedules.remove(schedule.getName());
        } else {
            throw new Exception("No schedule named " + schedule.getName() + " found");
        }
    }

    @Override
    public Collection<Schedule> loadAllSchedules() {
        return this.schedules.values();
    }
}
