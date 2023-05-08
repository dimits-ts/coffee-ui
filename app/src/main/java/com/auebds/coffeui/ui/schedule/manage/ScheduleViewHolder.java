package com.auebds.coffeui.ui.schedule.manage;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.auebds.coffeui.R;


/**
 * A view holder linking a schedule to its XML layout.
 *
 * @author Dimitris Tsirmpas
 */
class ScheduleViewHolder extends RecyclerView.ViewHolder {
    private final TextView textView;

    public ScheduleViewHolder(@NonNull View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.textView);

        // TODO: Define click listener for the ViewHolder's View
        itemView.setOnClickListener(view -> System.out.println("pressed"));
    }

    public TextView getTextView() {
        return textView;
    }
}
