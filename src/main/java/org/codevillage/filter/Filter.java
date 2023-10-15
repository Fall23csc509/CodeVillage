package org.codevillage.filter;

import java.util.ArrayList;

public class Filter {

    public static ArrayList<Building> byString(ArrayList<Building> buildings, String query){
        return FilterByString.filter(buildings, query);
    }

    public static ArrayList<Building> bySelection(ArrayList<Building> buildings, ArrayList<Building> selection){
        return FilterBySelection.filter(buildings, selection);
    }

    public static ArrayList<Building> byCategory(ArrayList<Building> buildings, String categoryName){
        return FilterByCategory.filter(buildings, categoryName);
    }

    public static ArrayList<Building> byPackage(ArrayList<Building> buildings, String packageName){
        return FilterByPackage.filter(buildings, packageName);
    }
}
