package com.auebds.coffeui.ui.drinks.chocolate;

public class CreateChocolateView {

    private final CreateChocolateActivity activity;

    public CreateChocolateView(CreateChocolateActivity activity) {
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

    public void setChocolate(int amount){
        this.activity.setChocolate(amount);
    }

    public void setTemperature(boolean temp){
        this.activity.setTemperature(temp);
    }


}
