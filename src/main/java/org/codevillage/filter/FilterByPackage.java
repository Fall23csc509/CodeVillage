package org.codevillage.filter;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FilterByPackage {
    public static ArrayList<JavaClass> filter(ArrayList<JavaClass> javaClasses, String packageName){
        return javaClasses.stream()
                .filter(javaClass -> javaClass.getPackageName().equals(packageName))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
