package com.auebds.coffeui.ui.schedule.create;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.auebds.coffeui.MainMenuActivity;
import com.auebds.coffeui.R;
import com.auebds.coffeui.dao.DebugScheduleDao;
import com.auebds.coffeui.databinding.ActivityCreateScheduleBinding;
import com.auebds.coffeui.entity.Day;
import com.auebds.coffeui.entity.DrinkType;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * The Activity that manages the creation of a schedule.
 *
 * @author Dimitris Tsirmpas
 */
public class CreateScheduleActivity extends AppCompatActivity {

    private final CreateScheduleMvp.CreateSchedulePresenter presenter =
            new CreateSchedulePresenter(new CreateScheduleView(this), new DebugScheduleDao());

    private ActivityCreateScheduleBinding binding;
    private Map<Day, Button> dayButtonHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateScheduleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.dayButtonHashMap = this.createDayButtonMap();

        this.attachRadioButtonListeners();
        this.assignDayButtonListeners();
        this.assignBackButtonListener();
        this.setUpSpinner();

        Button saveButton = binding.editScheduleButton;
        saveButton.setOnClickListener(view -> this.presenter.save());

        binding.timePicker.setIs24HourView(true); // because of user feedback
    }

    String getName() {
        return binding.scheduleNameField.getText().toString();
    }

    /**
     * Get the drink selected by the user.
     * @return the selected DrinkType
     * @throws IllegalArgumentException if the id used in the spinner does not correspond to
     * any drink type.
     * @implNote The id is selected from the first character of the spinner. This means that
     * any translation must include it as a first character, in the 1-4 range.
     */
    DrinkType getSelectedDrink() throws IllegalArgumentException {
        String drinkName = (String) binding.selectDrinkSpinner.getSelectedItem();
        int drinkId = Character.getNumericValue(drinkName.trim().charAt(0));

        if(drinkId < 0) {
            throw new IllegalArgumentException(String.format(Locale.getDefault(),
                    "No id for drink type found in spinner value %s. See @implNote", drinkName));
        }

        return Arrays.stream(DrinkType.values())
                .filter(drinkType -> drinkType.getId() == drinkId)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format(Locale.getDefault(),
                        "Id %d not valid for any drink type. See @implNote", drinkId)));
                // we don't care about app locale since this is a programming error
    }

    /**
     * Get the user-selected scheduled time.
     * @return a LocalTime object containing the time or null if the time is invalid
     */
    LocalTime getTime() {
        if(binding.timePicker.validateInput())
            return LocalTime.of(binding.timePicker.getHour(), binding.timePicker.getMinute());
        else
            return null;
    }

    /**
     * Make the button corresponding to a specific day clickable.
     * @param day the day whose button will be made clickable
     */
    void makeClickable(Day day) {
        Button button = this.dayButtonHashMap.get(day);
        assert button != null;
        button.setEnabled(true);
    }

    /**
     * Make the button corresponding to a specific day un-clickable.
     * @param day the day whose button will be made un-clickable
     */
    void makeUnclickable(Day day) {
        Button button = this.dayButtonHashMap.get(day);
        assert button != null;
        button.setEnabled(false);
    }

    /**
     * Change the appearance of the button corresponding to a specific day to being selected.
     * @param day the day whose button will be marked as selected
     */
    void markSelected(Day day) {
        Button button = this.dayButtonHashMap.get(day);
        assert button != null;
        button.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                R.color.button_selected));
    }

    /**
     * Change the appearance of the button corresponding to a specific day to being un-selected.
     * @param day the day whose button will be marked as un-selected
     */
    void markUnselected(Day day) {
        Button button = this.dayButtonHashMap.get(day);
        assert button != null;
        button.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),
                R.color.primary_white));
    }

    View getRootView() {
        return getWindow().getDecorView().getRootView();
    }

    void toMenu() {
        Intent menuIntent = new Intent(CreateScheduleActivity.this, MainMenuActivity.class);
        startActivity(menuIntent);
    }

    String getStringRes(@StringRes int stringId, String... args) {
        return getString(stringId, (Object []) args);
    }

    private void setUpSpinner() {
        Spinner spinner = binding.selectDrinkSpinner;

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.drinks_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setSelection(0);
    }

    /**
     * Make the back button go to the main menu when pressed.
     */
    private void assignBackButtonListener() {
        ImageView backButton = binding.buttonBack;
        backButton.setOnClickListener(view -> toMenu());
    }


    @SuppressLint("ClickableViewAccessibility") //TODO: subclass buttons?
    private void assignDayButtonListeners() {
        for(Map.Entry<Day, Button> entry: this.dayButtonHashMap.entrySet()){
            entry.getValue().setOnTouchListener((view, event) -> {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    //view.performClick();
                    this.presenter.daySelected(entry.getKey());
                }
                return false; // don't need this event for anything else
            });
        }
    }

    /**
     * Notify the presenter if any of the choices are selected.
     */
    private void attachRadioButtonListeners() {
        binding.buttonRepeatOnce.setOnClickListener(view ->
                presenter.groupSelected(RepetitionPeriod.ONCE));
        binding.buttonRepeatDaily.setOnClickListener(view ->
                presenter.groupSelected(RepetitionPeriod.DAILY));
        binding.buttonRepeatWeekday.setOnClickListener(view ->
                presenter.groupSelected(RepetitionPeriod.WEEKDAY));
        binding.buttonRepeatWeekend.setOnClickListener(view ->
                presenter.groupSelected(RepetitionPeriod.WEEKEND));
        binding.buttonRepeatCustom.setOnClickListener(view ->
                presenter.groupSelected(RepetitionPeriod.CUSTOM));
    }

    /**
     * Create the internal map mapping days to their toggle buttons.
     * @return the filled map
     */
    private Map<Day, Button> createDayButtonMap() {
        HashMap<Day, Button> map = new HashMap<>();

        map.put(Day.MONDAY, binding.buttonMonday);
        map.put(Day.TUESDAY, binding.buttonTuesday);
        map.put(Day.WEDNESDAY, binding.buttonWendensday);
        map.put(Day.THURSDAY, binding.buttonThursday);
        map.put(Day.FRIDAY, binding.buttonFriday);
        map.put(Day.SATURDAY, binding.buttonSaturday);
        map.put(Day.SUNDAY, binding.buttonSunday);

        // check if accessed before initialization
        assert map.get(Day.MONDAY) != null;

        return map;
    }

}