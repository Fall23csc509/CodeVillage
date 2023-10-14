package org.codevillage.filter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterBySelection {

    public static Building[] filter(Building[] buildings, Building[] selection){
        List<String> selectedBuildingNames = Arrays.stream(selection)
                .map(Building::getName)  // Get the names of the selected buildings
                .collect(Collectors.toList());

        return Arrays.stream(buildings)
                .filter(building -> {
                    List<String> relatedBuildingNames = building.getRelations();  // Get related building names
                    for (String relatedName : relatedBuildingNames) {
                        if (selectedBuildingNames.contains(relatedName)) {
                            return true;  // The building is related to a selected building, so add it to the array
                        }
                    }
                    return false;
                })
                .toArray(Building[]::new);
    }
}
