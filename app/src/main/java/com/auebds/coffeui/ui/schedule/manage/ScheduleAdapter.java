package com.auebds.coffeui.ui.schedule.manage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.auebds.coffeui.R;
import com.auebds.coffeui.entity.Schedule;


/**
 * A list adapter for displaying schedules.
 *
 * @author Dimitris Tsirmpas
 */
class ScheduleAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {

    private final ManageScheduleMvp.ManageSchedulePresenter presenter;
    private final Schedule[] schedules;
    private final int selectedColorId;
    private final int defaultColorId;
    private ScheduleViewHolder selectedViewHolder;

    /**
     * Create a new list adapter containing the user-defined schedules.
     * @param presenter the presenter responsible for managing schedules
     * @param selectedColorId the color id in which the selected button will be painted
     * @param defaultColorId the color id in which the rest of the buttons will be painted
     */
    public ScheduleAdapter(ManageScheduleMvp.ManageSchedulePresenter presenter,
                           @ColorInt int selectedColorId, @ColorInt int defaultColorId) {
        this.presenter = presenter;
        this.schedules = presenter.getUserSchedules().toArray(new Schedule[0]);
        this.selectedColorId = selectedColorId;
        this.defaultColorId = defaultColorId;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_schedule, parent, false);
        final ScheduleViewHolder viewHolder = new ScheduleViewHolder(view);


        viewHolder.setOnClickListener(v -> {
            if(viewHolder != this.selectedViewHolder) {

                // notify the presenter the selected schedule has been changed
                presenter.switchDisplayedSchedule(viewHolder.getSchedule());

                // change the selected schedule button's color,
                // and make the previously selected button's color the default
                displayNonSelectedView(this.selectedViewHolder);
                displaySelectedView(viewHolder);

                // update pointer to selected view holder
                this.selectedViewHolder = viewHolder;
            }
        });

        // select first viewHolder
        if(this.selectedViewHolder == null) {
            this.displaySelectedView(viewHolder);
            this.selectedViewHolder = viewHolder;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        holder.setSchedule(schedules[position]);
    }

    @Override
    public int getItemCount() {
        return schedules.length;
    }

    private void displaySelectedView(ScheduleViewHolder viewHolder) {
        viewHolder.setColor(this.selectedColorId);
    }

    private void displayNonSelectedView(ScheduleViewHolder viewHolder) {
        viewHolder.setColor(this.defaultColorId);
    }

}
