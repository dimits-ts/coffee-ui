package com.auebds.coffeui.entity;

public class Espresso {
    private int cups;
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

    public void plusCups() {
        this.cups +=1;
    }

    public void minusCups(){
        this.cups -=1;
    }

    public void plusMilk() {
        this.milk += 1;
    }

    public void minusMilk(){
        this.milk -= 1;
    }

    public void setCoffee(int coffee) {
        this.coffee = coffee;
    }

    public void setTemp(boolean temp) {
        this.temp = temp;
    }

    public void setMilkType(boolean milkType) {
        this.milkType = milkType;
    }

    public Espresso(int cups, int milk, int sugar, int coffee, boolean temp, boolean milkType){
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