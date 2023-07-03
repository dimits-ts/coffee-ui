package com.auebds.coffeui.ui.drinks.chocolate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.auebds.coffeui.MainMenuActivity;
import com.auebds.coffeui.R;
import com.auebds.coffeui.dao.DrinkDao;
import com.auebds.coffeui.databinding.ActivityCreateChocolateBinding;
import com.auebds.coffeui.ui.tutorial.TutorialActivity;
import com.auebds.coffeui.util.Util;

public class CreateChocolateActivity extends AppCompatActivity {

    private final CreateChocolatePresenter presenter =
            new CreateChocolatePresenter(
                    new CreateChocolateView(this), DrinkDao.getInstance());
    private ActivityCreateChocolateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateChocolateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.assignBackButtonListener();
        this.attachListeners();
        this.attachRadioButtonListeners();

        Button saveButton = binding.goButton;
        saveButton.setOnClickListener(view -> this.presenter.save());

        ImageButton helpButton = binding.helpButton5;
        helpButton.setOnClickListener(view -> {
            Intent intent = new Intent(CreateChocolateActivity.this, TutorialActivity.class);
            Bundle b = new Bundle();
            b.putString("path", "android.resource://" + getPackageName() + "/" + R.raw.tutorial_create_chocolate);
            intent.putExtras(b);
            startActivity(intent);
        });

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
        menuIntent.putExtra(MainMenuActivity.ARG_MESSAGE, getString(R.string.preparing_chocolate));
        this.setResult(RESULT_OK, menuIntent);
        this.finish();
    }

    private void attachListeners() {
        binding.plusbuttonwater.setOnClickListener(view -> presenter.changeWater(true));
        binding.minusbuttonwater.setOnClickListener(view -> presenter.changeWater(false));
        binding.plusbuttonsugar.setOnClickListener(view -> presenter.changeSugar(true));
        binding.minusbuttonsugar.setOnClickListener(view -> presenter.changeSugar(false));
        binding.plusbuttonmilk.setOnClickListener(view -> presenter.changeMilk(true));
        binding.minusbuttonmilk.setOnClickListener(view -> presenter.changeMilk(false));
    }

    private void attachRadioButtonListeners() {
        binding.temperatureSwitch.setOnCheckedChangeListener(
                (compoundButton, b) -> presenter.changeTemperature(b));
    }

    public void setSugar(int amount){
        binding.sugarAmount.setText(Util.localizedToString(amount));
    }

    public void setMilk(int amount){
        binding.milkAmount.setText(Util.localizedToString(amount));
    }

    public void setWater(int amount){
        binding.waterAmount.setText(Util.localizedToString(amount));
    }

    public void setChocolate(int amount) {binding.chocolateAmount.setText(Util.localizedToString(amount));}


    public void setTemperature(boolean temp) {
        binding.temperatureSwitch.setChecked(temp);
    }

    public int getSugar() {
        return Integer.parseInt(binding.sugarAmount.getText().toString());
    }

    public int getMilk() {
        return Integer.parseInt(binding.milkAmount.getText().toString());
    }

    public int getWater() {
        return Integer.parseInt(binding.waterAmount.getText().toString());
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