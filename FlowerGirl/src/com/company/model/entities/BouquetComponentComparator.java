package com.company.model.entities;

import java.util.Comparator;

public class BouquetComponentComparator implements Comparator<Plant> {
    @Override
    public int compare(Plant a, Plant b){
        return b.getFreshnessLevel() - a.getFreshnessLevel();
    }
}
