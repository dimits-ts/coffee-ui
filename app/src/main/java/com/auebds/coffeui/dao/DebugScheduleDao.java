package com.auebds.coffeui.dao;
import com.auebds.coffeui.entity.Day;
import com.auebds.coffeui.entity.DrinkType;
import com.auebds.coffeui.entity.Schedule;

import java.time.LocalTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


/**
 * A default implementation of the schedule DAO to be used until replaced by an
 * actual concrete implementation.
 *
 * @author Dimitris Tsirmpas
 */
public class DebugScheduleDao implements ScheduleDao {

    /**
     * Get the active instance of the ScheduleDao.
     * @return the dao
     */
    public static DebugScheduleDao getInstance() {
        return DebugScheduleDao.dao;
    }

    private static final DebugScheduleDao dao = new DebugScheduleDao();

    private final Map<String, Schedule> schedules = new HashMap<>();

    private DebugScheduleDao() {
        LinkedList<Day> dayList = new LinkedList<>();
        dayList.add(Day.MONDAY);
        dayList.add(Day.SUNDAY);
        dayList.add(Day.SATURDAY);
        Schedule demoSchedule = new Schedule("DemoSched", true,
               dayList, LocalTime.now(), DrinkType.ESPRESSO);

        LinkedList<Day> dayList2 = new LinkedList<>();
        dayList2.add(Day.TUESDAY);
        dayList2.add(Day.WEDNESDAY);
        dayList2.add(Day.SUNDAY);
        dayList2.add(Day.MONDAY);
        dayList2.add(Day.THURSDAY);
        Schedule demoSchedule2 = new Schedule("DemoSched2", false, dayList2,
                LocalTime.now(), DrinkType.HOT_CHOCOLATE);

        this.schedules.put(demoSchedule.getName(), demoSchedule);
        this.schedules.put(demoSchedule2.getName(), demoSchedule2);
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
