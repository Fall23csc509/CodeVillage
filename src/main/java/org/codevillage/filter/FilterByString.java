package org.codevillage.filter;

import java.util.Arrays;

public class FilterByString {
    public static Building[] filter(Building[] buildings, String query){
        return Arrays.stream(buildings)
                .filter(building -> building.getName().equals(query) || building.getDistrict().equals(query))
                .toArray(Building[]::new);
    }
}
