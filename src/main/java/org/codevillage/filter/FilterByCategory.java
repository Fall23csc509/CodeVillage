package org.codevillage.filter;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FilterByCategory {
    public static ArrayList<JavaClass> filter(ArrayList<JavaClass> javaClasses, String categoryName){
        return javaClasses.stream()
                .filter(javaClass -> javaClass.getCategory().equals(categoryName))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
