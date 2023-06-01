package com.auebds.coffeui.dao;

import com.auebds.coffeui.entity.Chocolate;
import com.auebds.coffeui.entity.Espresso;
import com.auebds.coffeui.entity.French;
import com.auebds.coffeui.entity.Tea;

public class DrinkDao {

    /**
     * Get the active instance of the DrinkDao.
     * @return the dao
     */
    public static DrinkDao getInstance() {
        return DrinkDao.dao;
    }

    private static final DrinkDao dao = new DrinkDao();

    private Chocolate chocolate;
    private Tea tea;
    private French french;
    private Espresso espresso;

    private DrinkDao(Chocolate chocolate, Tea tea, French french, Espresso espresso){
        this.chocolate=chocolate;
        this.tea=tea;
        this.french=french;
        this.espresso=espresso;
    }

    private DrinkDao(){
        this.chocolate=null;
        this.tea=null;
        this.french=null;
        this.espresso=null;
    }

    public Chocolate getChocolate(){
        return this.chocolate;
    }

    public void setChocolate(Chocolate chocolate){
        this.chocolate=chocolate;
    }

    public Tea getTea(){
        return this.tea;
    }

    public void setTea(Tea tea){
        this.tea=tea;
    }

    public French getFrench(){
        return this.french;
    }

    public void setFrench(French french){
        this.french=french;
    }

    public Espresso getEspresso() {
        return espresso;
    }

    public void setEspresso(Espresso espresso){
        this.espresso=espresso;
    }
}
