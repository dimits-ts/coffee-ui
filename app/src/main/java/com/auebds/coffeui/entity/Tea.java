package com.auebds.coffeui.entity;

public class Tea {
    private int cups;
    private int milk;
    private int sugar;
    private boolean temp;

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

    public void setTemp(boolean temp) {
        this.temp = temp;
    }

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
