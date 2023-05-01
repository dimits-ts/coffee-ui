package com.auebds.coffeui.ui.schedule.manage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private static final int MAX_NAME_SIZE = 20;
    private final Schedule[] schedules;

    /**
     * Create a new list adapter containing the user-defined schedules.
     * @param schedules the saved schedules
     */
    public ScheduleAdapter(Schedule[] schedules) {
        this.schedules = schedules;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_schedule, parent, false);

        return new ScheduleViewHolder(view);
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
}
