package com.auebds.coffeui.dao;
import com.auebds.coffeui.entity.Day;
import com.auebds.coffeui.entity.DrinkType;
import com.auebds.coffeui.entity.Schedule;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * A default implementation of the schedule DAO to be used until replaced by an
 * actual concrete implementation.
 *
 * @author Dimitris Tsirmpas
 */
public class DebugScheduleDao implements ScheduleDao {

    private final Map<String, Schedule> schedules = new HashMap<>();

    public DebugScheduleDao() {
        LinkedList<Day> dayList = new LinkedList<>();
        dayList.add(Day.MONDAY);
        dayList.add(Day.SUNDAY);
        dayList.add(Day.SATURDAY);

        Schedule demoSchedule = new Schedule("DemoSched", true,
               dayList, LocalTime.now(), DrinkType.ESPRESSO, true);
        this.schedules.put(demoSchedule.getName(), demoSchedule);
    }

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
