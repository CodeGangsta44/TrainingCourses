package com.company.model.entities;

/**
 * Class that is the root of the plant hierarchy.
 * Implements the interface BouquetComponent
 *
 * @author Roman Dovhopoliuk
 * @version 1.0.0
 */
public class Plant implements BouquetComponent {
    /** Attribute name of the plant */
    private String name;

    /** Attribute price of the plant */
    private int price;

    /** Attribute freshness level of the plant */
    private int freshnessLevel;


    /**
     * Constructor for creating new object
     * @param name - name of the plant
     * @param price - price of the plant
     * @param freshnessLevel - freshness level of the plant
     */
    public Plant(String name, int price, int freshnessLevel) {
        this.name = name;
        this.price = price;
        this.freshnessLevel = freshnessLevel;

    }

    /**
     * Implemented method of BouquetComponent interface
     * @see BouquetComponent
     * @return name of the component(plant)
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Implemented method of BouquetComponent interface
     * @see BouquetComponent
     * @return price of the component(plant)
     */
    @Override
    public int getPrice() {
        return this.price;
    }

    /**
     * Method for getting the plant`s freshness level
     * @return freshness level of the plant
     */
    public int getFreshnessLevel() {
        return this.freshnessLevel;
    }

    /**
     * Overridden method of superclass Object
     * @see Object
     * Method for getting a string representation of a Plant object.
     * @return string representation
     */
    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();

        result.append("Name: " + this.getName() + "\n");
        result.append("Price: " + this.getPrice() + "\n");
        result.append("Freshness level: " + this.getFreshnessLevel() + "\n");

        return result.toString();
    }
}
