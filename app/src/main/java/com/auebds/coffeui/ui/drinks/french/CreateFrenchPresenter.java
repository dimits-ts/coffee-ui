package com.auebds.coffeui.ui.drinks.french;

import com.auebds.coffeui.dao.DrinkDao;
import com.auebds.coffeui.entity.DrinkLimits;
import com.auebds.coffeui.entity.French;


public class CreateFrenchPresenter {

    private final French defaultFrench;

    private final CreateFrenchView view;
    private final DrinkDao dao;

    public CreateFrenchPresenter(CreateFrenchView view, DrinkDao dao){
        this.view=view;
        this.dao=dao;
        if (this.dao.getFrench() != null){
            this.defaultFrench = this.dao.getFrench();
        }
        else{
            this.defaultFrench = new French(200,2,2,2,true, true);
        }
    }

    public void loadLastPreset() {
        this.view.setMilk(this.defaultFrench.getMilk());
        this.view.setSugar(this.defaultFrench.getSugar());
        this.view.setWater(this.defaultFrench.getWater());
        this.view.setCoffee(this.defaultFrench.getCoffee());
        this.view.setTemperature(this.defaultFrench.getTemp());
        this.view.setMilkType(this.defaultFrench.getMilkType());
    }

    public void changeSugar(boolean increment){
        if (increment && this.defaultFrench.getSugar() < DrinkLimits.MAX_SUGAR){
            this.defaultFrench.plusSugar();
        }
        else if (!increment && this.defaultFrench.getSugar() > DrinkLimits.MIN_SUGAR) {
            this.defaultFrench.minusSugar();
        }
        this.view.setSugar(this.defaultFrench.getSugar());
    }

    public void changeMilk(boolean increment){
        if (increment && this.defaultFrench.getMilk() < DrinkLimits.MAX_MILK){
            this.defaultFrench.plusMilk();
        }
        else if (!increment && this.defaultFrench.getMilk() > DrinkLimits.MIN_MILK) {
            this.defaultFrench.minusMilk();
        }
        this.view.setMilk(this.defaultFrench.getMilk());
    }

    public void changeWater(boolean increment){
        if (increment && this.defaultFrench.getWater() < DrinkLimits.MAX_WATER){
            this.defaultFrench.plusWater();
        }
        else if (!increment && this.defaultFrench.getWater() > DrinkLimits.MIN_WATER){
            this.defaultFrench.minusWater();
        }
        this.view.setWater(this.defaultFrench.getWater());
    }

    public void changeCoffee(boolean increment){
        if (increment && this.defaultFrench.getCoffee() < DrinkLimits.MAX_INGREDIENT){
            this.defaultFrench.plusCoffee();
        }
        else if (!increment && this.defaultFrench.getCoffee() > DrinkLimits.MIN_INGREDIENT){
            this.defaultFrench.minusCoffee();
        }
        this.view.setCoffee(this.defaultFrench.getCoffee());
    }

    public void changeTemperature(boolean temp){
        this.defaultFrench.setTemp(temp);
        this.view.setTemperature(temp);
    }

    public void changeMilkType(boolean type){
        this.defaultFrench.setMilkType(type);
        this.view.setMilkType(type);
    }

    public void save(){
        dao.setFrench(this.defaultFrench);
        view.displaySuccess();
    }
}
