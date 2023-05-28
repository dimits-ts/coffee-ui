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
    }

    public void loadLastPreset() {
        this.view.setMilk(this.defaultFrench.getMilk());
        this.view.setSugar(this.defaultFrench.getSugar());
        this.view.setCups(this.defaultFrench.getCups());
        this.view.setCoffee(this.defaultFrench.getCoffee());
        this.view.setTemperature(this.defaultFrench.getTemp());
        this.view.setMilkType(this.defaultFrench.getMilkType());
    }

    public void changeSugar(boolean increment){
        if (increment){
            this.defaultFrench.plusSugar();
        }
        else{
            this.defaultFrench.minusSugar();
        }
        this.view.setSugar(this.defaultFrench.getSugar());
    }

    public void changeMilk(boolean increment){
        if (increment){
            this.defaultFrench.plusMilk();
        }
        else{
            this.defaultFrench.minusMilk();
        }
        this.view.setMilk(this.defaultFrench.getMilk());
    }

    public void changeCups(boolean increment){
        if (increment){
            this.defaultFrench.plusCups();
        }
        else{
            this.defaultFrench.minusCups();
        }
        this.view.setCups(this.defaultFrench.getCups());
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
