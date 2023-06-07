package com.auebds.coffeui.entity;

public class Chocolate {
    private int water;
    private int milk;
    private int sugar;
    private int chocolate;

    public void plusSugar() {
        this.sugar += 1;
    }

    public void minusSugar(){
        this.sugar -= 1;
    }

    public void plusWater() {
        this.water += DrinkLimits.WATER_CHANGE_INTERVAL;
    }

    public void minusWater(){
        this.water -= DrinkLimits.WATER_CHANGE_INTERVAL;
    }

    public void plusMilk() {
        this.milk += 1;
    }

    public void minusMilk(){
        this.milk -= 1;
    }

    public void plusChocolate() {
        this.chocolate +=1;
    }

    public void minusChocolate(){ this.chocolate -=1; }

    public void setTemp(boolean temp) {
        this.temp = temp;
    }

    private boolean temp;

    public Chocolate(int water, int milk, int sugar, int chocolate, boolean temp){
        this.water=water;
        this.milk=milk;
        this.sugar=sugar;
        this.chocolate=chocolate;
        this.temp=temp;
    }

    public int getWater(){
        return this.water;
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
