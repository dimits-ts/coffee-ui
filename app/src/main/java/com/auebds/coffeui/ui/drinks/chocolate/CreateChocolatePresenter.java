package com.auebds.coffeui.ui.drinks.chocolate;

import com.auebds.coffeui.dao.DrinkDao;
import com.auebds.coffeui.entity.Chocolate;
import com.auebds.coffeui.entity.DrinkLimits;


public class CreateChocolatePresenter {

    private final Chocolate defaultChocolate;

    private final CreateChocolateView view;
    private final DrinkDao dao;

    public CreateChocolatePresenter(CreateChocolateView view, DrinkDao dao){
        this.view=view;
        this.dao=dao;
        if (this.dao.getChocolate() != null){
            this.defaultChocolate = this.dao.getChocolate();
        }
        else{
            this.defaultChocolate = new Chocolate(250,2,2,2,true);
        }
    }

    public void loadLastPreset() {
        this.view.setMilk(this.defaultChocolate.getMilk());
        this.view.setSugar(this.defaultChocolate.getSugar());
        this.view.setWater(this.defaultChocolate.getWater());
        this.view.setChocolate(this.defaultChocolate.getChocolate());
        this.view.setTemperature(this.defaultChocolate.getTemp());
    }

    public void changeSugar(boolean increment){
        if (increment && this.defaultChocolate.getSugar() < DrinkLimits.MAX_SUGAR){
            this.defaultChocolate.plusSugar();
        }
        else if (!increment && this.defaultChocolate.getSugar() > DrinkLimits.MIN_SUGAR) {
            this.defaultChocolate.minusSugar();
        }
        this.view.setSugar(this.defaultChocolate.getSugar());
    }

    public void changeMilk(boolean increment){
        if (increment && this.defaultChocolate.getMilk() < DrinkLimits.MAX_MILK){
            this.defaultChocolate.plusMilk();
        }
        else if (!increment && this.defaultChocolate.getMilk() > DrinkLimits.MIN_MILK) {
            this.defaultChocolate.minusMilk();
        }
        this.view.setMilk(this.defaultChocolate.getMilk());
    }

    public void changeWater(boolean increment){
        if (increment && this.defaultChocolate.getWater() < DrinkLimits.MAX_WATER){
            this.defaultChocolate.plusWater();
        }
        else if (!increment && this.defaultChocolate.getWater() > DrinkLimits.MIN_WATER){
            this.defaultChocolate.minusWater();
        }
        this.view.setWater(this.defaultChocolate.getWater());
    }

    public void changeChocolate(boolean increment){
        if (increment && this.defaultChocolate.getChocolate() < DrinkLimits.MAX_INGREDIENT){
            this.defaultChocolate.plusChocolate();
        }
        else if (!increment && this.defaultChocolate.getChocolate() > DrinkLimits.MIN_INGREDIENT){
            this.defaultChocolate.minusChocolate();
        }
        this.view.setChocolate(this.defaultChocolate.getChocolate());
    }

    public void changeTemperature(boolean temp){
        this.defaultChocolate.setTemp(temp);
        this.view.setTemperature(temp);
    }

    public void save(){
        dao.setChocolate(this.defaultChocolate);
        view.displaySuccess();
    }
}
