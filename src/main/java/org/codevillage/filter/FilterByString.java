package org.codevillage.filter;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FilterByString {
    public static ArrayList<Building> filter(ArrayList<Building> buildings, String query){
        return buildings.stream()
                .filter(building -> building.getName().equals(query) || building.getDistrict().equals(query))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
