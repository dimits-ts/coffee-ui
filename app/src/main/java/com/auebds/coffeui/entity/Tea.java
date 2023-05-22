package com.auebds.coffeui.entity;

public class Tea {
    private final int cups;
    private final int milk;
    private final int sugar;
    private final boolean temp;

    public Tea(int cups, int milk, int sugar, boolean temp){
        this.cups=cups;
        this.milk=milk;
        this.sugar=sugar;
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

    public boolean getTemp(){
        return this.temp;
    }
}
