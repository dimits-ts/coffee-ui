package com.auebds.coffeui.ui.drinks;

import com.auebds.coffeui.dao.DrinkDao;
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
            this.defaultTea = new Tea(1,2,2,true);
        }
        //TODO: figure out what the fuck to do with this
        /*this.view.setMilk(this.defaultChocolate.getMilk());
        this.view.setSugar(this.defaultChocolate.getSugar());
        this.view.setCups(this.defaultChocolate.getCups());*/
    }

    public void changeSugar(boolean increment){
        if (increment){
            this.defaultTea.plusSugar();
            this.view.setSugar(this.defaultTea.getSugar());
        }
        else{
            this.defaultTea.minusSugar();
            this.view.setSugar(this.defaultTea.getSugar());
        }
    }

    public void changeMilk(boolean increment){
        if (increment){
            this.defaultTea.plusMilk();
            this.view.setMilk(this.defaultTea.getMilk());
        }
        else{
            this.defaultTea.minusMilk();
            this.view.setMilk(this.defaultTea.getMilk());
        }
    }

    public void changeCups(boolean increment){
        if (increment){
            this.defaultTea.plusCups();
            this.view.setCups(this.defaultTea.getCups());
        }
        else{
            this.defaultTea.minusCups();
            this.view.setCups(this.defaultTea.getCups());
        }
    }

    public void changeTemperature(boolean temp){
        this.view.setTemperature(temp);
    }

    public void save(){
        dao.setTea(this.defaultTea);
        view.displaySuccess();
    }
}
