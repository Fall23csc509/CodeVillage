package org.codevillage.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterBySelection {

    public static ArrayList<Building> filter(ArrayList<Building> buildings, ArrayList<Building> selection){
        List<String> selectedBuildingNames = selection.stream()
                .map(Building::getName)  // Get the names of the selected buildings
                .collect(Collectors.toList());

        return buildings.stream()
                .filter(building -> {
                    ArrayList<String> relatedBuildingNames = building.getRelations();  // Get related building names
                    for (String relatedName : relatedBuildingNames) {
                        if (selectedBuildingNames.contains(relatedName)) {
                            return true;  // The building is related to a selected building, so add it to the array
                        }
                    }
                    return false;
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
