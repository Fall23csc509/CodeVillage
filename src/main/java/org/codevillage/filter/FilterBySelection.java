package org.codevillage.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterBySelection {

    public static ArrayList<Building> filter(ArrayList<Building> buildings, ArrayList<Building> selection){
        List<String> relatedBuildings = new ArrayList<String>();
        selection.stream()
                .flatMap(building -> building.getRelations().stream())
                .forEach(relatedBuildings::add);; // Get the names of the related buildings

        ArrayList<Building> newSelection = buildings.stream()
                .filter(building -> {
                    return relatedBuildings.contains(building.getName());
                })
                .collect(Collectors.toCollection(ArrayList<Building>::new));

        newSelection.addAll(selection);
        return newSelection.stream().distinct().collect(Collectors.toCollection(ArrayList<Building>::new)); // Enforce uniqueness


    }
}
