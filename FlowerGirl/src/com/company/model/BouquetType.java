package com.company.model;

import com.company.model.entities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum BouquetType {
    EXPEENCIVE(new Flower("Bluebell", 100, 90, 25),
               new Flower("Bluebell", 100, 100, 25),
               new Flower("Bluebell", 100, 80, 25),
               new Flower("Rose", 50, 53, 50),
               new Flower("Rose", 50, 59, 50),
               new Flower("Rose", 50, 57, 50),
               new Flower("Rose", 50, 35, 50),
               new Flower("Rose", 50, 56, 50),
               new Grass("Lavender", 50, 50),
               new BouquetAccessory("Paper", 50),
               new BouquetAccessory("Strip", 10)),

    NORMAL(new Flower("Rose", 50, 53, 50),
           new Flower("Rose", 50, 59, 50),
           new Flower("Rose", 50, 57, 50),
           new Flower("Rose", 50, 35, 50),
           new Flower("Rose", 50, 56, 50),
           new Grass("Lavender", 50, 50),
           new BouquetAccessory("Paper", 50),
           new BouquetAccessory("Strip", 10)),

    CHEAP(new Flower("Daisy", 15, 59, 30),
          new Flower("Daisy", 15, 87, 30),
          new Flower("Daisy", 15, 55, 30),
          new BouquetAccessory("Strip", 10));

    private List<BouquetComponent> components;

    private BouquetType(BouquetComponent... components) {
        this.components = new ArrayList<>(Arrays.asList(components));
    }

    public List<BouquetComponent> getComponents() {
        return this.components;
    }

}
