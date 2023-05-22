package com.auebds.coffeui.entity;

public class French {
    private final int cups;
    private final int milk;
    private final int sugar;
    private final int coffee;
    private final boolean temp;

    private final boolean milkType;

    public French(int cups, int milk, int sugar, int coffee, boolean temp, boolean milkType){
        this.cups=cups;
        this.milk=milk;
        this.sugar=sugar;
        this.coffee=coffee;
        this.temp=temp;
        this.milkType=milkType;
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

    public int getCoffee(){
        return this.coffee;
    }

    public boolean getTemp(){
        return this.temp;
    }

    public boolean getMilkType(){
        return milkType;
    }
}
