package com.auebds.coffeui.ui.drinks.tea;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.auebds.coffeui.MainMenuActivity;
import com.auebds.coffeui.R;
import com.auebds.coffeui.dao.DrinkDao;

public class CreateTeaActivity extends AppCompatActivity {

    private final CreateTeaPresenter presenter =
            new CreateTeaPresenter(new CreateTeaView(this), new DrinkDao());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tea);

        this.assignBackButtonListener();
        this.attachListeners();
        this.attachRadioButtonListeners();

        Button saveButton = findViewById(R.id.goButton4);
        saveButton.setOnClickListener(view -> this.presenter.save());
    }

    View getRootView() {
        return getWindow().getDecorView().getRootView();
    }


    /**
     * Make the back button go to the main menu when pressed.
     */
    private void assignBackButtonListener() {
        ImageView backButton = findViewById(R.id.buttonBack);
        backButton.setOnClickListener(view -> toMenu());
    }

    void toMenu() {
        Intent menuIntent = new Intent(CreateTeaActivity.this, MainMenuActivity.class);
        startActivity(menuIntent);
    }

    private void attachListeners() {
        Button cupsPlus = findViewById(R.id.plusbuttoncups);
        Button cupsMinus = findViewById(R.id.minusbuttoncups);
        Button sugarPlus = findViewById(R.id.plusbuttonsugar);
        Button sugarMinus = findViewById(R.id.minusbuttonsugar);
        Button milkPlus = findViewById(R.id.plusbuttonmilk);
        Button milkMinus = findViewById(R.id.minusbuttonmilk);

        cupsPlus.setOnClickListener(view -> presenter.changeCups(true));
        cupsMinus.setOnClickListener(view -> presenter.changeCups(false));
        sugarPlus.setOnClickListener(view -> presenter.changeSugar(true));
        sugarMinus.setOnClickListener(view -> presenter.changeSugar(false));
        milkPlus.setOnClickListener(view -> presenter.changeMilk(true));
        milkMinus.setOnClickListener(view -> presenter.changeMilk(false));
    }

    private void attachRadioButtonListeners() {
        RadioButton hotButton = findViewById(R.id.hotbutton);
        RadioButton coldButton = findViewById(R.id.buttoncold);


        hotButton.setOnClickListener(view -> presenter.changeTemperature(true));
        coldButton.setOnClickListener(view -> presenter.changeTemperature(false));
    }

    public void setSugar(int amount){
        TextView tv = (TextView) findViewById(R.id.sugarAmount);
        tv.setText(Integer.toString(amount));
    }

    public void setMilk(int amount){
        TextView tv = (TextView) findViewById(R.id.milkAmount);
        tv.setText(Integer.toString(amount));
    }

    public void setCups(int amount){
        TextView tv = (TextView) findViewById(R.id.cupsAmount);
        tv.setText(Integer.toString(amount));
    }

    public void setChocolate(int amount) {
        switch(amount){
            case 1:
                findViewById(R.id.amount1).setEnabled(true);
                break;
            case 2:
                findViewById(R.id.amount2).setEnabled(true);
                break;
            case 3:
                findViewById(R.id.amount3).setEnabled(true);
                break;
            case 4:
                findViewById(R.id.amount4).setEnabled(true);
                break;
        }
    }

    public void setTemperature(boolean temp) {
        if(temp){findViewById(R.id.hotbutton).setEnabled(true);}
        else    {findViewById(R.id.buttoncold).setEnabled(true);}
    }

    public int getSugar() {
        TextView tv = (TextView) findViewById(R.id.sugarAmount);
        return Integer.parseInt(tv.getText().toString());
    }

    public int getMilk() {
        TextView tv = (TextView) findViewById(R.id.milkAmount);
        return Integer.parseInt(tv.getText().toString());
    }

    public int getCups() {
        TextView tv = (TextView) findViewById(R.id.cupsAmount);
        return Integer.parseInt(tv.getText().toString());
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