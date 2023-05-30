package com.auebds.coffeui.ui.drinks.tea;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.auebds.coffeui.MainMenuActivity;
import com.auebds.coffeui.R;
import com.auebds.coffeui.dao.DrinkDao;
import com.auebds.coffeui.databinding.ActivityCreateTeaBinding;
import com.auebds.coffeui.util.Util;

public class CreateTeaActivity extends AppCompatActivity {

    private final CreateTeaPresenter presenter =
            new CreateTeaPresenter(new CreateTeaView(this), DrinkDao.getInstance());

    private ActivityCreateTeaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCreateTeaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.assignBackButtonListener();
        this.attachListeners();
        this.attachRadioButtonListeners();

        Button saveButton = binding.goButton4;
        saveButton.setOnClickListener(view -> this.presenter.save());

        presenter.loadLastPreset();
    }


    /**
     * Make the back button go to the main menu when pressed.
     */
    private void assignBackButtonListener() {
        binding.buttonBack.setOnClickListener(view -> toMenu());
    }

    void toMenu() {
        this.finish();
    }

    void toMenuWithMessage() {
        Intent menuIntent = new Intent();
        menuIntent.putExtra(MainMenuActivity.ARG_MESSAGE, getString(R.string.preparing_tea));
        this.setResult(RESULT_OK, menuIntent);
        this.finish();
    }
    private void attachListeners() {
        binding.plusbuttoncups.setOnClickListener(view -> presenter.changeCups(true));
        binding.minusbuttoncups.setOnClickListener(view -> presenter.changeCups(false));
        binding.plusbuttonsugar.setOnClickListener(view -> presenter.changeSugar(true));
        binding.minusbuttonsugar.setOnClickListener(view -> presenter.changeSugar(false));
        binding.plusbuttonmilk.setOnClickListener(view -> presenter.changeMilk(true));
        binding.minusbuttonmilk.setOnClickListener(view -> presenter.changeMilk(false));
    }

    private void attachRadioButtonListeners() {
        binding.hotbutton.setOnClickListener(view -> presenter.changeTemperature(true));
        binding.buttoncold.setOnClickListener(view -> presenter.changeTemperature(false));
    }

    public void setSugar(int amount){
        binding.sugarAmount.setText(Util.localizedToString(amount));
    }

    public void setMilk(int amount){
        binding.milkAmount.setText(Util.localizedToString(amount));
    }

    public void setCups(int amount){
        binding.cupsAmount.setText(Util.localizedToString(amount));
    }

    public void setTemperature(boolean temp) {
        if(temp){binding.hotbutton.setChecked(true);}
        else    {binding.buttoncold.setChecked(true);}
    }

    public int getSugar() {
        return Integer.parseInt(binding.sugarAmount.getText().toString());
    }

    public int getMilk() {
        return Integer.parseInt(binding.milkAmount.getText().toString());
    }

    public int getCups() {
        return Integer.parseInt(binding.cupsAmount.getText().toString());
    }

    public boolean getTemp() {
        Button button;
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        button = findViewById(selectedRadioButtonId);
        String text = button.getText().toString();
        return (text.equalsIgnoreCase("hot"));
    }


}