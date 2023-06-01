package com.auebds.coffeui.ui.drinks.french;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.auebds.coffeui.MainMenuActivity;
import com.auebds.coffeui.R;
import com.auebds.coffeui.dao.DrinkDao;
import com.auebds.coffeui.databinding.ActivityCreateFrCoffeeBinding;
import com.auebds.coffeui.util.Util;

public class CreateFrenchActivity extends AppCompatActivity {

    private final CreateFrenchPresenter presenter =
            new CreateFrenchPresenter(new CreateFrenchView(this), DrinkDao.getInstance());
    private ActivityCreateFrCoffeeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateFrCoffeeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.assignBackButtonListener();
        this.attachListeners();
        this.attachRadioButtonListeners();

        Button saveButton = binding.goButton2;
        saveButton.setOnClickListener(view -> this.presenter.save());

        presenter.loadLastPreset();
    }


    /**
     * Make the back button go to the main menu when pressed.
     */
    private void assignBackButtonListener() {
        ImageView backButton = binding.buttonBack;
        backButton.setOnClickListener(view -> toMenu());
    }

    void toMenu() {
        this.finish();
    }

    void toMenuWithMessage() {
        Intent menuIntent = new Intent();
            menuIntent.putExtra(MainMenuActivity.ARG_MESSAGE, getString(R.string.preparing_french));
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
        binding.temperatureSwitch.setOnCheckedChangeListener(
                (CompoundButton buttonView, boolean isChecked)
                        -> presenter.changeTemperature(isChecked));

        binding.milkSwitch.setOnCheckedChangeListener(
                (CompoundButton buttonView, boolean isChecked)
                        -> presenter.changeMilkType(isChecked));

        binding.amount1.setOnClickListener(view -> presenter.changeCoffee(1));
        binding.amount2.setOnClickListener(view -> presenter.changeCoffee(2));
        binding.amount3.setOnClickListener(view -> presenter.changeCoffee(3));
        binding.amount4.setOnClickListener(view -> presenter.changeCoffee(4));
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

    public void setCoffee(int amount) {
        switch(amount){
            case 1:
                binding.amount1.setChecked(true);
                break;
            case 2:
                binding.amount2.setChecked(true);
                break;
            case 3:
                binding.amount3.setChecked(true);
                break;
            case 4:
                binding.amount4.setChecked(true);
                break;
        }
    }

    public void setTemperature(boolean temp) {
        binding.temperatureSwitch.setChecked(temp);
    }

    public void setMilkType(boolean type){
        binding.milkSwitch.setChecked(type);
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