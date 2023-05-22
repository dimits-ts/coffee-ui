package com.auebds.coffeui.entity;

public class Chocolate {
    private final int cups;
    private final int milk;
    private final int sugar;
    private final int chocolate;
    private final boolean temp;

    public Chocolate(int cups, int milk, int sugar, int chocolate, boolean temp){
        this.cups=cups;
        this.milk=milk;
        this.sugar=sugar;
        this.chocolate=chocolate;
        this.temp=temp;
    }

    public int getCups(){
        return this.cups;
    }

    public int getMilk(){
        return this.milk;
    }

    public int getSugar(){
        return this.sugar;
    }

    public int getChocolate(){
        return this.chocolate;
    }

    public boolean getTemp(){
        return this.temp;
    }
}
