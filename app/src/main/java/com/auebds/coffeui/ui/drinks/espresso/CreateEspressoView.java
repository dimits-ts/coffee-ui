package com.auebds.coffeui.ui.drinks.espresso;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class CreateEspressoView {

    private final CreateEspressoActivity activity;

    private final static int SNACKBAR_DURATION = BaseTransientBottomBar.LENGTH_SHORT;

    public CreateEspressoView(CreateEspressoActivity activity) {
        this.activity = activity;
    }

    public void displaySuccess() {
        Snackbar.make(this.activity.getRootView(), "Espresso Template Successfully Saved", SNACKBAR_DURATION).show();
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

    public void setCoffee(int amount){
        this.activity.setCoffee(amount);
    }

    public void setTemperature(boolean temp){
        this.activity.setTemperature(temp);
    }

    public void setMilkType(boolean type){this.activity.setMilkType(type);}


}