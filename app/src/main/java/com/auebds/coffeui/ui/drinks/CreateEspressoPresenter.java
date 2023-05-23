package com.auebds.coffeui.ui.drinks;

import com.auebds.coffeui.dao.DrinkDao;
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
        //TODO: figure out what the fuck to do with this
        /*this.view.setMilk(this.defaultChocolate.getMilk());
        this.view.setSugar(this.defaultChocolate.getSugar());
        this.view.setCups(this.defaultChocolate.getCups());*/
    }

    public void changeSugar(boolean increment){
        if (increment){
            this.defaultEspresso.plusSugar();
            this.view.setSugar(this.defaultEspresso.getSugar());
        }
        else{
            this.defaultEspresso.minusSugar();
            this.view.setSugar(this.defaultEspresso.getSugar());
        }
    }

    public void changeMilk(boolean increment){
        if (increment){
            this.defaultEspresso.plusMilk();
            this.view.setMilk(this.defaultEspresso.getMilk());
        }
        else{
            this.defaultEspresso.minusMilk();
            this.view.setMilk(this.defaultEspresso.getMilk());
        }
    }

    public void changeCups(boolean increment){
        if (increment){
            this.defaultEspresso.plusCups();
            this.view.setCups(this.defaultEspresso.getCups());
        }
        else{
            this.defaultEspresso.minusCups();
            this.view.setCups(this.defaultEspresso.getCups());
        }
    }

    public void changeCoffee(int amount){
        this.view.setCoffee(amount);
    }

    public void changeTemperature(boolean temp){
        this.view.setTemperature(temp);
    }

    public void changeMilkType(boolean type){this.view.setMilkType(type);}

    public void save(){
        dao.setEspresso(this.defaultEspresso);
        view.displaySuccess();
    }
}
