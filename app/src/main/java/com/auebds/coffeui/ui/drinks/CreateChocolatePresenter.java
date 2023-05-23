package com.auebds.coffeui.ui.drinks;

import com.auebds.coffeui.dao.DrinkDao;
import com.auebds.coffeui.entity.Chocolate;


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
            this.defaultChocolate = new Chocolate(1,2,2,2,true);
        }
        //TODO: figure out what the fuck to do with this
        /*this.view.setMilk(this.defaultChocolate.getMilk());
        this.view.setSugar(this.defaultChocolate.getSugar());
        this.view.setCups(this.defaultChocolate.getCups());*/
    }

    public void changeSugar(boolean increment){
        if (increment){
            this.defaultChocolate.plusSugar();
            this.view.setSugar(this.defaultChocolate.getSugar());
        }
        else{
            this.defaultChocolate.minusSugar();
            this.view.setSugar(this.defaultChocolate.getSugar());
        }
    }

    public void changeMilk(boolean increment){
        if (increment){
            this.defaultChocolate.plusMilk();
            this.view.setMilk(this.defaultChocolate.getMilk());
        }
        else{
            this.defaultChocolate.minusMilk();
            this.view.setMilk(this.defaultChocolate.getMilk());
        }
    }

    public void changeCups(boolean increment){
        if (increment){
            this.defaultChocolate.plusCups();
            this.view.setCups(this.defaultChocolate.getCups());
        }
        else{
            this.defaultChocolate.minusCups();
            this.view.setCups(this.defaultChocolate.getCups());
        }
    }

    public void changeChocolate(int amount){
        this.defaultChocolate.setChocolate(amount);
        this.view.setChocolate(amount);
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
