package com.auebds.coffeui.ui.drinks.tea;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class CreateTeaView {

    private final CreateTeaActivity activity;

    private final static int SNACKBAR_DURATION = BaseTransientBottomBar.LENGTH_SHORT;

    public CreateTeaView(CreateTeaActivity activity) {
        this.activity = activity;
    }

    public void displaySuccess() {
        Snackbar.make(this.activity.getRootView(), "Tea Template Successfully Saved", SNACKBAR_DURATION).show();
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

    public void setTemperature(boolean temp){
        this.activity.setTemperature(temp);
    }


}
