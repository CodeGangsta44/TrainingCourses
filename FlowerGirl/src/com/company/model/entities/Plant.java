package com.company.model.entities;

public class Plant implements BouquetComponent{
    private String name;
    private int price;
    private int freshnessLevel;

    public Plant(String name, int price, int freshnessLevel) {
        this.name = name;
        this.price = price;
        this.freshnessLevel = freshnessLevel;

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    public int getFreshnessLevel() {
        return this.freshnessLevel;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();

        result.append("Name: " + this.getName() + "\n");
        result.append("Price: " + this.getPrice() + "\n");
        result.append("Freshness level: " + this.getFreshnessLevel() + "\n");

        return result.toString();
    }
}
