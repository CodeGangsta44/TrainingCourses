package com.company.model.entities;

/**
 * Сlass for bouquet accessories (paper, stripes, etc.)
 * Implements the interface BouquetComponent
 *
 * @author Roman Dovhopoliuk
 * @version 1.0.0
 */
public class BouquetAccessory implements BouquetComponent {
    /** Attribute name of the accessory */
    private String name;

    /** Attribute price of the accessory */
    private int price;

    /**
     * Constructor for creating new object
     * @param name - name of the accessory
     * @param price - price of the accessory
     */
    public BouquetAccessory(String name, int price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Implemented method of BouquetComponent interface
     * @see BouquetComponent
     * @return name of the component(accessory)
     */
    @Override
    public String getName(){
        return this.name;
    }


    /**
     * Implemented method of BouquetComponent interface
     * @see BouquetComponent
     * @return price of the component(accessory)
     */
    @Override
    public int getPrice(){
        return this.price;
    }


    /**
     * Overridden method of superclass Object
     * @see Object
     * Method for getting a string representation of a BouquetAccessory object.
     * @return string representation
     */
    public String toString(){
        StringBuilder result = new StringBuilder();

        result.append("Name: " + this.getName() + "\n");
        result.append("Price: " + this.getPrice() + "\n");

        return result.toString();
    }

}
