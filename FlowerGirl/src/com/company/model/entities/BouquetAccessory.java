package com.company.model.entities;

public class BouquetAccessory implements BouquetComponent{
    private String name;
    private int price;

    public BouquetAccessory(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public int getPrice(){
        return this.price;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();

        result.append("Name: " + this.getName() + "\n");
        result.append("Price: " + this.getPrice() + "\n");

        return result.toString();
    }

}
