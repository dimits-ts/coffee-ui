package com.auebds.coffeui.ui.schedule.manage;

import com.auebds.coffeui.dao.ScheduleDao;
import com.auebds.coffeui.entity.Schedule;

import java.util.Collection;
import java.util.LinkedList;


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
        } catch(Exception exc) {
            view.displayError(exc.getLocalizedMessage());
        }
    }

    @Override
    public void toCreateScheduleActivity() {
        view.toCreateScheduleActivity();
    }
}
