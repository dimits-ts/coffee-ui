package com.auebds.coffeui.ui.schedule.manage;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.auebds.coffeui.R;
import com.auebds.coffeui.entity.Schedule;

import java.util.Collection;


/**
 * A list adapter for displaying schedules.
 *
 * @author Dimitris Tsirmpas
 */
class ScheduleAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {
    private static final int MAX_NAME_SIZE = 20;

    private final Schedule[] schedules;
    private final int selectedColorId;
    private final int defaultColorId;
    private ScheduleViewHolder selectedViewHolder;

    /**
     * Create a new list adapter containing the user-defined schedules.
     * @param schedules the saved schedules
     * @param selectedColorId the color id in which the selected button will be painted
     * @param defaultColorId the color id in which the rest of the buttons will be painted
     */
    public ScheduleAdapter(Collection<Schedule> schedules,
                           @ColorInt int selectedColorId, @ColorInt int defaultColorId) {
        this.schedules = schedules.toArray(new Schedule[0]);
        this.selectedColorId = selectedColorId;
        this.defaultColorId = defaultColorId;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_schedule, parent, false);
        final ScheduleViewHolder viewHolder = new ScheduleViewHolder(view);

        // change the selected schedule button's color,
        // and make the previously selected button's color the default
        viewHolder.getTextView().setOnClickListener(v -> {
            if(viewHolder != this.selectedViewHolder) {
                displayNonSelectedView(this.selectedViewHolder);
                displaySelectedView(viewHolder);

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
        String name = schedules[position].getName();
        if(name.length() > MAX_NAME_SIZE) {
            name = name.substring(0, MAX_NAME_SIZE) + "...";
        }

        holder.getTextView().setText(name);
    }

    @Override
    public int getItemCount() {
        return schedules.length;
    }

    private void displaySelectedView(ScheduleViewHolder viewHolder) {
        viewHolder.getTextView().setBackgroundTintList(ColorStateList.valueOf(this.selectedColorId));
    }

    private void displayNonSelectedView(ScheduleViewHolder viewHolder) {
        viewHolder.getTextView().setBackgroundTintList(ColorStateList.valueOf(this.defaultColorId));
    }

}
