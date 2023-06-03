package com.auebds.coffeui.entity;

public class Tea {
    private int water;
    private int milk;
    private int sugar;
    private boolean temp;

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

    public void setTemp(boolean temp) {
        this.temp = temp;
    }

    public Tea(int water, int milk, int sugar, boolean temp){
        this.water=water;
        this.milk=milk;
        this.sugar=sugar;
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

    public boolean getTemp(){
        return this.temp;
    }
}
