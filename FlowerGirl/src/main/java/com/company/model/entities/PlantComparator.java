package com.company.model.entities;

import java.util.Comparator;

/**
 * Class for comparing two objects of a class Plant.
 * @see Plant
 * Implements the interface Comparator.
 * @see Comparator
 *
 * @author Roman Dovhopoliuk
 * @version 1.0.0
 */
public class PlantComparator implements Comparator<Plant> {

    /**
     * Implemented method of Comparator interface
     * @see Comparator
     * @return integer value describing the order of the Plant objects
     */
    @Override
    public int compare(Plant a, Plant b){
        return b.getFreshnessLevel() - a.getFreshnessLevel();
    }
}
