package org.codevillage.filter;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FilterByString {
    public static ArrayList<JavaClass> filter(ArrayList<JavaClass> javaClasses, String query){
        return javaClasses.stream()
                .filter(javaClass -> javaClass.getName().equals(query) || javaClass.getPackageName().equals(query))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
