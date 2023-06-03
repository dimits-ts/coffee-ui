package com.auebds.coffeui.ui.drinks.tea;

import com.auebds.coffeui.dao.DrinkDao;
import com.auebds.coffeui.entity.DrinkLimits;
import com.auebds.coffeui.entity.Tea;


public class CreateTeaPresenter {

    private final Tea defaultTea;

    private final CreateTeaView view;
    private final DrinkDao dao;

    public CreateTeaPresenter(CreateTeaView view, DrinkDao dao){
        this.view=view;
        this.dao=dao;
        if (this.dao.getTea() != null){
            this.defaultTea = this.dao.getTea();
        }
        else{
            this.defaultTea = new Tea(150,2,2,true);
        }
    }

    public void loadLastPreset() {
        this.view.setMilk(this.defaultTea.getMilk());
        this.view.setSugar(this.defaultTea.getSugar());
        this.view.setWater(this.defaultTea.getWater());
        this.view.setTemperature(this.defaultTea.getTemp());
    }


    public void changeSugar(boolean increment){
        if (increment && this.defaultTea.getSugar() < DrinkLimits.MAX_SUGAR){
            this.defaultTea.plusSugar();
        }
        else if (!increment && this.defaultTea.getSugar() > DrinkLimits.MIN_SUGAR) {
            this.defaultTea.minusSugar();
        }
        this.view.setSugar(this.defaultTea.getSugar());
    }

    public void changeMilk(boolean increment){
        if (increment && this.defaultTea.getMilk() < DrinkLimits.MAX_MILK){
            this.defaultTea.plusMilk();
        }
        else if (!increment && this.defaultTea.getMilk() > DrinkLimits.MIN_MILK) {
            this.defaultTea.minusMilk();
        }
        this.view.setMilk(this.defaultTea.getMilk());
    }

    public void changeWater(boolean increment){
        if (increment && this.defaultTea.getWater() < DrinkLimits.MAX_WATER){
            this.defaultTea.plusWater();
        }
        else if (!increment && this.defaultTea.getWater() > DrinkLimits.MIN_WATER){
            this.defaultTea.minusWater();
        }
        this.view.setWater(this.defaultTea.getWater());
    }

    public void changeTemperature(boolean temp){
        this.defaultTea.setTemp(temp);
        this.view.setTemperature(temp);
    }

    public void save(){
        dao.setTea(this.defaultTea);
        view.displaySuccess();
    }
}
