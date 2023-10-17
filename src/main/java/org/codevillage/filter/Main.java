package org.codevillage.filter;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        filterSelections();
    }

    public static void filterSelections(){
        ArrayList<Building> testBuildings = new ArrayList<Building>();
        Building building1 = new Building("test1", 1, 2, "district1", new ArrayList<String>(){{
            add("test2");}
        }, "class");

        Building building2 = new Building("test2", 1, 2, "district2", new ArrayList<String>(){{
            add("test3");}
        }, "class");

        Building building3 = new Building("test3", 1, 2, "district3", new ArrayList<String>(){{
            add("test1");
        add("test3");}
        }, "class");

        testBuildings.add(building1);
        testBuildings.add(building2);
        testBuildings.add(building3);

        ArrayList<Building> testSelection = new ArrayList<Building>(){
            {
                add(building1);
//                add(building2);
            }
        };
        System.out.println(Filter.bySelection(testBuildings, testSelection).toString());
    }
}