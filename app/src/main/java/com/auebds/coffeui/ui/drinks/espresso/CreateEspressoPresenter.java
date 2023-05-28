package com.auebds.coffeui.ui.drinks.espresso;

import com.auebds.coffeui.dao.DrinkDao;
import com.auebds.coffeui.entity.DrinkLimits;
import com.auebds.coffeui.entity.Espresso;


public class CreateEspressoPresenter {

    private final Espresso defaultEspresso;

    private final CreateEspressoView view;
    private final DrinkDao dao;

    public CreateEspressoPresenter(CreateEspressoView view, DrinkDao dao){
        this.view=view;
        this.dao=dao;
        if (this.dao.getEspresso() != null){
            this.defaultEspresso = this.dao.getEspresso();
        }
        else{
            this.defaultEspresso = new Espresso(1,2,2,2,true, true);
        }
    }

    public void loadLastPreset() {
        this.view.setMilk(this.defaultEspresso.getMilk());
        this.view.setSugar(this.defaultEspresso.getSugar());
        this.view.setCups(this.defaultEspresso.getCups());
        this.view.setCoffee(this.defaultEspresso.getCoffee());
        this.view.setTemperature(this.defaultEspresso.getTemp());
        this.view.setMilkType(this.defaultEspresso.getMilkType());
    }

    public void changeSugar(boolean increment){
        if (increment && this.defaultEspresso.getSugar() < DrinkLimits.MAX_SUGAR){
            this.defaultEspresso.plusSugar();
        }
        else if (!increment && this.defaultEspresso.getSugar() > DrinkLimits.MIN_SUGAR) {
            this.defaultEspresso.minusSugar();
        }
        this.view.setSugar(this.defaultEspresso.getSugar());
    }

    public void changeMilk(boolean increment){
        if (increment && this.defaultEspresso.getMilk() < DrinkLimits.MAX_MILK){
            this.defaultEspresso.plusMilk();
        }
        else if (!increment && this.defaultEspresso.getMilk() > DrinkLimits.MIN_MILK) {
            this.defaultEspresso.minusMilk();
        }
        this.view.setMilk(this.defaultEspresso.getMilk());
    }

    public void changeCups(boolean increment){
        if (increment && this.defaultEspresso.getCups() < DrinkLimits.MAX_CUPS){
            this.defaultEspresso.plusCups();
        }
        else if (!increment && this.defaultEspresso.getCups() > DrinkLimits.MIN_CUPS){
            this.defaultEspresso.minusCups();
        }
        this.view.setCups(this.defaultEspresso.getCups());
    }

    public void changeCoffee(int amount){
        this.defaultEspresso.setCoffee(amount);
        this.view.setCoffee(amount);
    }

    public void changeTemperature(boolean temp){
        this.defaultEspresso.setTemp(temp);
        this.view.setTemperature(temp);
    }

    public void changeMilkType(boolean type){
        this.defaultEspresso.setMilkType(type);
        this.view.setMilkType(type);
    }

    public void save(){
        dao.setEspresso(this.defaultEspresso);
        view.displaySuccess();
    }
}
