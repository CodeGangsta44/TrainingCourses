package com.company.model.entities;

/**
 * An interface that should implement each item in the bouquet
 * @author Roman Dovhopoliuk
 * @version 1.0.0
 */
public interface BouquetComponent {
    /**
     * Method for getting the component`s name
     * @return name of the component
     */
    String getName();

    /**
     * Method for getting the component`s price
     * @return price of the component
     */
    int getPrice();
}
