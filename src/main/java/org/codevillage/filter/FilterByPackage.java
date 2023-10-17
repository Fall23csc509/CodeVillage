package org.codevillage.filter;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Fulfils requirement 19. Select buildings in the Village that match a package (the district)
 */
public class FilterByPackage {
    public static ArrayList<JavaClass> filter(ArrayList<JavaClass> javaClasses, String packageName){
        return javaClasses.stream()
                .filter(javaClass -> javaClass.getPackageName().equals(packageName))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
