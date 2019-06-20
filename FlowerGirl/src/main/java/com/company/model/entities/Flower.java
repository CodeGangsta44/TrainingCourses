package com.company.model.entities;


/**
 * Class that is part of the plant hierarchy.
 * Inherits class Plant.
 * Logical representation of the flower.
 *
 * @author Roman Dovhopoliuk
 * @version 1.0.0
 */
public class Flower extends Plant{
    /** Attribute length of stem of the flower */
    private int stemLength;


    /**
     * Constructor for creating new object
     * @param name - name of the flower
     * @param price - price of the flower
     * @param freshnessLevel - freshness level of the flower
     * @param stemLength - length of stem of the flower
     */
    public Flower(String name, int price, int freshnessLevel, int stemLength) {
        super(name, price, freshnessLevel);
        this.stemLength = stemLength;

    }

    /**
     * Method for getting the flower`s stem length
     * @return stem length of the flower
     */
    public int getStemLength() {
        return this.stemLength;
    }

    /**
     * Overridden method of superclass Object
     * @see Object
     * Method for getting a string representation of a Flower object.
     * @return string representation
     */
    public String toString(){
        StringBuilder result = new StringBuilder();

        result.append(super.toString());
        result.append("Stem length: " + this.getStemLength() + "\n");

        return result.toString();
    }
}
