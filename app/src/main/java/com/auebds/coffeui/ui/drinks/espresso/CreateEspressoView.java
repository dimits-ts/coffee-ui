package com.auebds.coffeui.ui.drinks.espresso;

public class CreateEspressoView {

    private final CreateEspressoActivity activity;

    public CreateEspressoView(CreateEspressoActivity activity) {
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

    public void setCoffee(int amount){
        this.activity.setCoffee(amount);
    }

    public void setTemperature(boolean temp){
        this.activity.setTemperature(temp);
    }

    public void setMilkType(boolean type){this.activity.setMilkType(type);}


}