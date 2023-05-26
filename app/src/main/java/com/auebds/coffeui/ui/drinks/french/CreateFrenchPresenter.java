package com.auebds.coffeui.ui.drinks.french;

import com.auebds.coffeui.dao.DrinkDao;
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
            this.defaultFrench = new French(1,2,2,2,true, true);
        }
        //TODO: figure out what the fuck to do with this
        /*this.view.setMilk(this.defaultChocolate.getMilk());
        this.view.setSugar(this.defaultChocolate.getSugar());
        this.view.setCups(this.defaultChocolate.getCups());*/
    }

    public void changeSugar(boolean increment){
        if (increment){
            this.defaultFrench.plusSugar();
            this.view.setSugar(this.defaultFrench.getSugar());
        }
        else{
            this.defaultFrench.minusSugar();
            this.view.setSugar(this.defaultFrench.getSugar());
        }
    }

    public void changeMilk(boolean increment){
        if (increment){
            this.defaultFrench.plusMilk();
            this.view.setMilk(this.defaultFrench.getMilk());
        }
        else{
            this.defaultFrench.minusMilk();
            this.view.setMilk(this.defaultFrench.getMilk());
        }
    }

    public void changeCups(boolean increment){
        if (increment){
            this.defaultFrench.plusCups();
            this.view.setCups(this.defaultFrench.getCups());
        }
        else{
            this.defaultFrench.minusCups();
            this.view.setCups(this.defaultFrench.getCups());
        }
    }

    public void changeCoffee(int amount){
        this.defaultFrench.setCoffee(amount);
        this.view.setCoffee(amount);
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
