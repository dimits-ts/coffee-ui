package com.auebds.coffeui.ui.schedule.manage;

import android.util.Log;

import com.auebds.coffeui.dao.ScheduleDao;
import com.auebds.coffeui.entity.Schedule;

import java.util.Collection;
import java.util.LinkedList;

/**
 * The concrete implementation of the Presenter for the ManageSchedule MVP contract.
 *
 * @author Dimitris Tsirmpas
 */
class ManageSchedulePresenter implements ManageScheduleMvp.ManageSchedulePresenter {
    private final ManageScheduleMvp.ManageScheduleView view;
    private final ScheduleDao dao;

    public ManageSchedulePresenter(ManageScheduleMvp.ManageScheduleView view, ScheduleDao dao) {
        this.view = view;
        this.dao = dao;
    }

    @Override
    public Collection<Schedule> getUserSchedules() {
        try {
            return dao.loadAllSchedules();
        } catch(Exception exc) {
            view.displayError(exc.getLocalizedMessage());
            return new LinkedList<>();
        }
    }

    @Override
    public void deleteSchedule(Schedule schedule) {
        try {
            dao.delete(schedule);
            this.initializeDisplaySchedule();
        } catch(Exception exc) {
            view.displayError(exc.getLocalizedMessage());
        }
    }

    @Override
    public void toCreateScheduleActivity() {
        view.toCreateScheduleActivity();
    }

    @Override
    public void initializeDisplaySchedule() {
        try{
            Collection<Schedule> schedules = dao.loadAllSchedules();
            if(schedules.isEmpty()) {
                this.view.setUpNoSchedulesFragment();
            } else {
                this.view.switchDisplayedSchedule(schedules.stream().findFirst().get());
            }
        } catch (Exception ioe) {
            Log.e("MANAGE_SCHEDULES", ioe.toString());
            this.view.setUpNoSchedulesFragment();
        }
    }

    @Override
    public void switchDisplayedSchedule(Schedule schedule) {
        assert schedule != null;
        this.view.switchDisplayedSchedule(schedule);
    }
}
