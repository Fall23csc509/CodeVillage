package org.codevillage.filter;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        filterSelections();
    }

    public static void filterSelections(){
        ArrayList<JavaClass> testBuildings = new ArrayList<JavaClass>();
        JavaClass building1 = new JavaClass("test1", 1, 2, "district1", new ArrayList<String>(){{
            add("test2");}
        }, "class");

        JavaClass building2 = new JavaClass("test2", 1, 2, "district2", new ArrayList<String>(){{
            add("test3");}
        }, "class");

        JavaClass building3 = new JavaClass("test3", 1, 2, "district3", new ArrayList<String>(){{
            add("test1");
        add("test3");}
        }, "class");

        testBuildings.add(building1);
        testBuildings.add(building2);
        testBuildings.add(building3);

        ArrayList<JavaClass> testSelection = new ArrayList<JavaClass>(){
            {
                add(building1);
//                add(building2);
            }
        };
        System.out.println(Filter.bySelection(testBuildings, testSelection).toString());
    }
}