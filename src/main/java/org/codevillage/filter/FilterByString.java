package org.codevillage.filter;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Fulfils requirement 18. Select buildings in the Village that match a search string
 */
public class FilterByString {
    public static ArrayList<JavaClass> filter(ArrayList<JavaClass> javaClasses, String query){
        return javaClasses.stream()
                .filter(javaClass -> javaClass.getName().equals(query) || javaClass.getPackageName().equals(query))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
