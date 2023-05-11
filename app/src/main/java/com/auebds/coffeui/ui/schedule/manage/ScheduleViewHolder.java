package com.auebds.coffeui.ui.schedule.manage;

import android.content.res.ColorStateList;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.auebds.coffeui.R;
import com.auebds.coffeui.entity.Schedule;


/**
 * A view holder linking a schedule to its XML layout.
 *
 * @author Dimitris Tsirmpas
 */
class ScheduleViewHolder extends RecyclerView.ViewHolder {
    private static final int MAX_NAME_SIZE = 20;

    private final TextView textView;
    private Schedule schedule;

    public ScheduleViewHolder(@NonNull View itemView) {
        super(itemView);
        this.textView = itemView.findViewById(R.id.textView);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        textView.setOnClickListener(listener);
    }

    public void setColor(@ColorInt int color) {
        this.textView.setBackgroundTintList(ColorStateList.valueOf(color));
    }

    /**
     * Set the schedule this view holder displays. Automatically changes the View's appearance.
     * @param schedule the schedule to be displayed
     * @throws RuntimeException if the schedule is null or if it has already been set
     */
    public void setSchedule(@NonNull Schedule schedule) throws RuntimeException {
        // assertions should always be checked, even if the IDE does not like it
        if(schedule == null) {
            throw new NullPointerException("Provided a null schedule for Schedule View Holder");
        } else {
            this.schedule = schedule;
            this.textView.setText(schedule.getName());
        }
    }

    public Schedule getSchedule() {
        return this.schedule;
    }

    private String formatName(String originalName) {
        if(originalName.length() > MAX_NAME_SIZE) {
            originalName = originalName.substring(0, MAX_NAME_SIZE) + "...";
        }
        return originalName;
    }
}
