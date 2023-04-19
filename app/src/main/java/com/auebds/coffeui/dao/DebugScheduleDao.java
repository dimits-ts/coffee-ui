package com.auebds.coffeui.dao;
import com.auebds.coffeui.entity.Schedule;

import java.io.IOException;
import java.util.HashSet;

/**
 * A default implementation of the schedule DAO to be used until replaced by an
 * actual concrete implementation.
 *
 * @author Dimitris Tsirmpas
 */
public class DebugScheduleDao implements ScheduleDao {

    @Override
    public void save(Schedule schedule) throws ScheduleNameException, IOException {
        System.out.println("Saved schedule with name " + schedule.getName());
    }

    @Override
    public void delete(Schedule schedule) throws IOException {
        System.out.println("Deleted schedule with name " + schedule.getName());
    }

    @Override
    public void edit(Schedule schedule) throws IOException {
        System.out.println("Edited schedule with name " + schedule.getName());
    }

    @Override
    public Iterable<Schedule> loadAllSchedules() throws IOException {
        return new HashSet<>();
    }
}
