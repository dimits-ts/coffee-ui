package com.auebds.coffeui.entity;

public class Chocolate {
    private int cups;
    private int milk;
    private int sugar;
    private int chocolate;

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

    public void setChocolate(int chocolate) {
        this.chocolate = chocolate;
    }

    public void setTemp(boolean temp) {
        this.temp = temp;
    }

    private boolean temp;

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
