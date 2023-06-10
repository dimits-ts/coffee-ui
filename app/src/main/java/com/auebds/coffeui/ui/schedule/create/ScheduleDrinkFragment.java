package com.auebds.coffeui.ui.schedule.create;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.auebds.coffeui.R;
import com.auebds.coffeui.databinding.FragmentScheduleDrinkBinding;
import com.auebds.coffeui.databinding.FragmentScheduleNameBinding;
import com.auebds.coffeui.entity.DrinkType;

import java.util.Arrays;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScheduleDrinkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScheduleDrinkFragment extends Fragment implements Switchable {

    private static final String ARG_PRESENTER = "PRESENTER";

    private FragmentScheduleDrinkBinding binding;
    private CreateScheduleMvp.CreateSchedulePresenter presenter;

    public ScheduleDrinkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param presenter the MVP presenter
     * @return A new instance of fragment ScheduleNameFragment.
     */
    public static ScheduleDrinkFragment newInstance(CreateScheduleMvp.CreateSchedulePresenter presenter) {
        ScheduleDrinkFragment fragment = new ScheduleDrinkFragment();
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
        binding = FragmentScheduleDrinkBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    /**
     * Set the drink selected by the user.
     * @throws IllegalArgumentException if the id used in the spinner does not correspond to
     * any drink type.
     * @implNote The id is selected from the first character of the spinner. This means that
     * any translation must include it as a first character, in the 1-4 range.
     */
    @Override
    public void onSwitch() {
        String drinkName = (String) binding.selectDrinkSpinner.getSelectedItem();
        int drinkId = Character.getNumericValue(drinkName.trim().charAt(0));

        if(drinkId < 0) {
            throw new IllegalArgumentException(String.format(Locale.getDefault(),
                    "No id for drink type found in spinner value %s. See @implNote", drinkName));
        }

        DrinkType type = Arrays.stream(DrinkType.values())
                .filter(drinkType -> drinkType.getId() == drinkId)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format(Locale.getDefault(),
                        "Id %d not valid for any drink type. See @implNote", drinkId)));
        // we don't care about app locale since this is a programming error

        this.presenter.setDrinkType(type);
    }
}