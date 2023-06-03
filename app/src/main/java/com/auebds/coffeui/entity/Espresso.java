package com.auebds.coffeui.entity;

public class Espresso {
    private int water;
    private int milk;
    private  int sugar;
    private int coffee;
    private boolean temp;

    private boolean milkType;

    public void plusSugar() {
        this.sugar += 1;
    }

    public void minusSugar(){
        this.sugar -= 1;
    }

    public void plusWater() {
        this.water +=25;
    }

    public void minusWater(){
        this.water -=25;
    }

    public void plusMilk() {
        this.milk += 1;
    }

    public void minusMilk(){
        this.milk -= 1;
    }

    public void plusCoffee() {
        this.coffee +=1;
    }

    public void minusCoffee(){this.coffee -=1;}

    public void setTemp(boolean temp) {
        this.temp = temp;
    }

    public void setMilkType(boolean milkType) {
        this.milkType = milkType;
    }

    public Espresso(int water, int milk, int sugar, int coffee, boolean temp, boolean milkType){
        this.water=water;
        this.milk=milk;
        this.sugar=sugar;
        this.coffee=coffee;
        this.temp=temp;
        this.milkType=milkType;
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