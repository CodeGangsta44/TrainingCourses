package com.company.model.entities;

public class Flower extends Plant{
    private int stemLength;

    public Flower(String name, int price, int freshnessLevel, int stemLength) {
        super(name, price, freshnessLevel);
        this.stemLength = stemLength;

    }

    public int getStemLength() {
        return this.stemLength;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();

        result.append(super.toString());
        result.append("Stem length: " + this.getStemLength() + "\n");

        return result.toString();
    }
}
