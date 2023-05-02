package com.auebds.coffeui.ui.schedule.manage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.auebds.coffeui.R;
import com.auebds.coffeui.entity.Day;
import com.auebds.coffeui.entity.Schedule;

import java.time.format.DateTimeFormatter;
import java.util.Collection;

/**
 * A {@link Fragment} which displays information about a {@link Schedule}.
 *
 * @author Dimitris Tsirmpas
 */
public class ScheduleFragment extends Fragment {

    // the fragment initialization parameters
    private static final String ARG_SCHEDULE = "schedule";

    public ScheduleFragment() {
        // Required empty public constructor
    }

    /**
     * A factory method to create a new instance of
     * this fragment using the schedule parameter.
     *
     * @param schedule the schedule which will be represented by the fragment.
     * @return A new instance of fragment ScheduleFragment.
     */
    public static ScheduleFragment newInstance(Schedule schedule) {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_SCHEDULE, schedule);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            Schedule schedule = getArguments().getParcelable(ARG_SCHEDULE);
            this.displayInfo(schedule);
        } else {
            Log.e("SCHEDULE_FRAGMENT", "null schedule passed to new fragment");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    private void displayInfo(Schedule schedule) {
        TextView typeView = requireView().findViewById(R.id.scheduleDrinkTypeLabel);
        typeView.setText(schedule.getName());

        TextView frequencyView = requireView().findViewById(R.id.scheduleFrequencyLabel);
        frequencyView.setText(formatFrequency(schedule.getDays()));

        TextView timeView = requireView().findViewById(R.id.scheduleTimeLabel);
        timeView.setText(schedule.getTime().format(DateTimeFormatter.ofPattern("H : m")));

        TextView activatedView = requireView().findViewById(R.id.scheduleLabelStatus);
        int resCode =  schedule.isActive() ? R.string.yes: R.string.no;
        activatedView.setText(getString(resCode));
    }

    private String formatFrequency(Collection<Day> days) {
        StringBuilder sb = new StringBuilder();

        for(Day day: days) {
            sb.append(day.toString().substring(0, 2));
            sb.append(",");
        }

        return sb.substring(0, sb.length()-1); // remove last comma
    }

}