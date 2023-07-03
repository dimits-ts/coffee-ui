package com.auebds.coffeui.ui.drinks.tea;

public class CreateTeaView {

    private final CreateTeaActivity activity;

    public CreateTeaView(CreateTeaActivity activity) {
        this.activity = activity;
    }

    public void displaySuccess() {
        this.activity.toMenuWithMessage();
    }

    public void toMenu() {
        this.activity.toMenu();
    }

    public void setSugar(int amount){
        this.activity.setSugar(amount);
    }

    public void setWater(int amount){
        this.activity.setWater(amount);
    }

    public void setMilk(int amount){
        this.activity.setMilk(amount);
    }

    public void setTemperature(boolean temp){
        this.activity.setTemperature(temp);
    }


}
