package com.company.model;

import com.company.model.entities.BouquetComponent;
import com.company.model.entities.Flower;
import com.company.model.entities.Plant;
import com.company.model.entities.PlantComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Class for a logical representation of the entity of a bouquet.
 * Main class of project model.
 *
 * @author Roman Dovhopoliuk
 * @version 1.0.0
 */

public class Bouquet {

    /** Attribute list of components of the bouquet
     * @see List
     */
    private List<BouquetComponent> components;

    /**
     * Constructor for creating new object
     */
    public Bouquet() {
        this.components = new ArrayList<>();
    }

    /**
     * Constructor for creating new object
     * @param components - set of components of the bouquet
     */
    public Bouquet(BouquetComponent... components) {
        this.components = new ArrayList<>(Arrays.asList(components));
    }

    /**
     * Constructor for creating new object
     * @param components - set of components of the bouquet
     */
    public Bouquet(List<BouquetComponent> components) {
        this.components = new ArrayList<>(components);
    }

    /**
     * Method for getting the bouquet`s price
     * @return price of the bouquet
     */
    public int getPrice() {
        int totalPrice = 0;

        for (BouquetComponent i : components) {
            totalPrice += i.getPrice();
        }

        return totalPrice;
    }


    /**
     * Method for getting list of all the bouquet`s components
     * @return list of bouquet`s components
     */
    public List<BouquetComponent> getComponents() {
        return this.components;
    }

    /**
     * Method for getting sorted by freshness list of all the bouquet`s plants
     * @see PlantComparator
     * @return sorted list of bouquet`s plants
     */
    public List<Plant> getSortedByFreshnessPlants() {
        List<Plant> plants = new ArrayList<>();

        for (BouquetComponent i : components) {
            if (i instanceof Plant) {
                plants.add((Plant)i);
            }
        }

        plants.sort(new PlantComparator());

        return plants;

    }

    /**
     * Method for getting filtered by stem length list of the bouquet`s flowers
     * @param lowerBound - lower bound of stem length
     * @param upperBound - upper bound of stem length
     * @return filtered list of bouquet`s flowers
     */
    public List<Flower> getFlowersByStemLength(int lowerBound, int upperBound) {
        List<Flower> flowers = new ArrayList<>();

        for (BouquetComponent i : components) {
            if (i instanceof Flower) {
                Flower currentFlower = (Flower)i;

                if (currentFlower.getStemLength() >= lowerBound && currentFlower.getStemLength() <= upperBound) {
                    flowers.add(currentFlower);
                }
            }
        }

        return flowers;
    }
}
