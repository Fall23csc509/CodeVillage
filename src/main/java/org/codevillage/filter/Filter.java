package org.codevillage.filter;

public class Filter {

    public static Building[] filter(Building[] buildings, String query){
        return FilterByString.filter(buildings, query);
    }

    public static Building[] filter(Building[] buildings, Building[] selection){
        return FilterBySelection.filter(buildings, selection);
    }
}
