package com.company.model.entities;

/**
 * Class that is part of the plant hierarchy.
 * Inherits class Plant.
 * Logical representation of the grass.
 *
 * @author Roman Dovhopoliuk
 * @version 1.0.0
 */

public class Grass extends Plant {

    /**
     * Constructor for creating new object
     * @param name - name of the grass
     * @param price - price of the grass
     * @param freshnessLevel - freshness level of the grass
     */
    public Grass(String name, int price, int freshnessLevel) {
        super(name, price, freshnessLevel);
    }

}
