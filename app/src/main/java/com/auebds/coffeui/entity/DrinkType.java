package com.auebds.coffeui.entity;

/**
 * The available drinks for the coffee machine.
 *
 * @author Dimitris Tsirmpas
 */
public enum DrinkType {
    ESPRESSO(1), FRENCH_COFFEE(2), TEA(3), HOT_CHOCOLATE(4);

    private final int id;

    DrinkType(int id){
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}