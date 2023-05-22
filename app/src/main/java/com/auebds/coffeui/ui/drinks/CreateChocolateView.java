package com.auebds.coffeui.ui.drinks;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class CreateChocolateView {

    private final CreateChocolateActivity activity;

    private final static int SNACKBAR_DURATION = BaseTransientBottomBar.LENGTH_SHORT;

    public CreateChocolateView(CreateChocolateActivity activity) {
        this.activity = activity;
    }

    public void displaySuccess() {
        Snackbar.make(this.activity.getRootView(), "Chocolate Template Successfully Saved", SNACKBAR_DURATION).show();
    }

    public void toMenu() {
        this.activity.toMenu();
    }

    public void setSugar(int amount){
        this.activity.setSugar(amount);
    }

    public void setCups(int amount){
        this.activity.setCups(amount);
    }

    public void setMilk(int amount){
        this.activity.setMilk(amount);
    }

    public void setChocolate(int amount){
        this.activity.setChocolate(amount);
    }

    public void setTemperature(boolean temp){
        this.activity.setTemperature(temp);
    }


}
