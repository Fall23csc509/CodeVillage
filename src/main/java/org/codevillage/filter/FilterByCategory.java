package org.codevillage.filter;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FilterByCategory {
    public static ArrayList<Building> filter(ArrayList<Building> buildings, String categoryName){
        return buildings.stream()
                .filter(building -> building.getCategory().equals(categoryName))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
