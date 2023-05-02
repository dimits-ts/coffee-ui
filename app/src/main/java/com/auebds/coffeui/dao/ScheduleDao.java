package com.auebds.coffeui.dao;

import com.auebds.coffeui.entity.Schedule;

import java.io.IOException;
import java.util.Collection;

/**
 * The interface for a DAO that manages the permanent storage of Schedule objects.
 * @author Dimitris Tsirmpas
 */
public interface ScheduleDao {

    /**
     * Save a schedule to permanent storage.
     * @param schedule the schedule to be saved
     * @throws ScheduleNameException in the case of a name conflict
     * @throws IOException if any error occurs while writing to permanent storage
     */
    void save(Schedule schedule) throws ScheduleNameException, IOException;

    /**
     * Delete an existing schedule from permanent storage.
     * @param schedule the schedule to be deleted
     * @throws IOException if the deletion failed
     * @throws Exception if the schedule doesn't exist
     */
    void delete(Schedule schedule) throws Exception, IOException;

    /**
     * Return all the current saved schedules.
     * @return an iterable containing all the saved schedules, empty if none
     * @throws IOException if the loading operation failed
     */
    Collection<Schedule> loadAllSchedules() throws IOException;
}
