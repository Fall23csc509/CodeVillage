package org.codevillage.filter;

import java.util.ArrayList;

public class Filter {

    public static ArrayList<JavaClass> byString(ArrayList<JavaClass> javaClasses, String query){
        return FilterByString.filter(javaClasses, query);
    }

    public static ArrayList<JavaClass> bySelection(ArrayList<JavaClass> javaClasses, ArrayList<JavaClass> selection){
        return FilterBySelection.filter(javaClasses, selection);
    }

    public static ArrayList<JavaClass> byCategory(ArrayList<JavaClass> javaClasses, String categoryName){
        return FilterByCategory.filter(javaClasses, categoryName);
    }

    public static ArrayList<JavaClass> byPackage(ArrayList<JavaClass> javaClasses, String packageName){
        return FilterByPackage.filter(javaClasses, packageName);
    }
}
