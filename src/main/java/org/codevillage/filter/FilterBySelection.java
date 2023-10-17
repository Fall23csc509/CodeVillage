package org.codevillage.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterBySelection {

    public static ArrayList<JavaClass> filter(ArrayList<JavaClass> javaClasses, ArrayList<JavaClass> selection){
        List<String> relatedBuildings = new ArrayList<String>();
        selection.stream()
                .flatMap(building -> building.getRelations().stream())
                .forEach(relatedBuildings::add);; // Get the names of the related javaClasses

        ArrayList<JavaClass> newSelection = javaClasses.stream()
                .filter(javaClass -> {
                    return relatedBuildings.contains(javaClass.getName());
                })
                .collect(Collectors.toCollection(ArrayList<JavaClass>::new));

        newSelection.addAll(selection);
        return newSelection.stream().distinct().collect(Collectors.toCollection(ArrayList<JavaClass>::new)); // Enforce uniqueness


    }
}
