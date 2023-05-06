package com.auebds.coffeui.ui.schedule.manage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.auebds.coffeui.R;

/**
 * A {@link Fragment} subclass to be shown when no schedules have been set by the user.
 * @author Dimitris Tsirmpas
 */
class NoSchedulesFragment extends Fragment {

    public NoSchedulesFragment() {
        // Required empty public constructor
    }

    public static NoSchedulesFragment newInstance() {
        NoSchedulesFragment fragment = new NoSchedulesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_no_schedules, container, false);
    }
}