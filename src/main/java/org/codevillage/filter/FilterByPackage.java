package org.codevillage.filter;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FilterByPackage {
    public static ArrayList<Building> filter(ArrayList<Building> buildings, String packageName){
        return buildings.stream()
                .filter(building -> building.getDistrict().equals(packageName))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
