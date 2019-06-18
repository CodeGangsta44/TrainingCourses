package com.company.model;

import com.company.model.entities.BouquetComponent;
import com.company.model.entities.BouquetComponentComparator;
import com.company.model.entities.Flower;
import com.company.model.entities.Plant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bouquet {
    private List<BouquetComponent> components;

    public Bouquet() {
        this.components = new ArrayList<>();
    }

    public Bouquet(BouquetComponent... components) {
        this.components = new ArrayList<>(Arrays.asList(components));
    }

    public Bouquet(List<BouquetComponent> components) {
        this.components = new ArrayList<>(components);
    }

    public int getPrice() {
        int totalPrice = 0;

        for (BouquetComponent i : components) {
            totalPrice += i.getPrice();
        }

        return totalPrice;
    }

    public List<BouquetComponent> getComponents() {
        return this.components;
    }

    public List<BouquetComponent> getSortedByFreshnessPlants() {
        List<Plant> plants = new ArrayList<>();

        for (BouquetComponent i : components) {
            if (i instanceof Plant) {
                plants.add((Plant)i);
            }
        }

        plants.sort(new BouquetComponentComparator());

        return new ArrayList<>(plants);

    }

    public List<BouquetComponent> getFlowersByStemLength(int lowerBound, int upperBound) {
        List<Flower> flowers = new ArrayList<>();

        for (BouquetComponent i : components) {
            if (i instanceof Flower) {
                Flower currentFlower = (Flower)i;

                if (currentFlower.getStemLength() > lowerBound && currentFlower.getStemLength() < upperBound) {
                    flowers.add(currentFlower);
                }
            }
        }

        return new ArrayList<>(flowers);
    }
}
