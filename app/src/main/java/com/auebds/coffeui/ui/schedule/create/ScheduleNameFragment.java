package com.auebds.coffeui.ui.schedule.create;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.auebds.coffeui.databinding.FragmentScheduleNameBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScheduleNameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScheduleNameFragment extends Fragment implements Switchable {

    private static final String ARG_PRESENTER = "PRESENTER";

    private FragmentScheduleNameBinding binding;
    private CreateScheduleMvp.CreateSchedulePresenter presenter;

    public ScheduleNameFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param presenter the MVP presenter
     * @return A new instance of fragment ScheduleNameFragment.
     */
    public static ScheduleNameFragment newInstance(CreateScheduleMvp.CreateSchedulePresenter presenter) {
        ScheduleNameFragment fragment = new ScheduleNameFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PRESENTER, presenter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.presenter = (CreateScheduleMvp.CreateSchedulePresenter) getArguments().getSerializable(ARG_PRESENTER);
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentScheduleNameBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onSwitch() {
        this.presenter.setName(binding.scheduleNameField.getText().toString());
    }
}