package org.codevillage;

import java.util.ArrayList;

public class Selector {
    private static Selector selector;
    private ArrayList<Building> buildings;
    private Selector() {
        buildings = new ArrayList<>();
    }
    public static Selector getSelector() {
        if (selector == null) {
            selector = new Selector();
        }
        return selector;
    }
    public ArrayList<Building> getBuildings() {
        return buildings;
    }
    public void addBuilding(Building building) {
        this.buildings.add(building);
    }
    public void removeBuilding(Building building) {
        this.buildings.remove(building);
    }
    public void clearBuildings() {
        this.buildings.clear();
    }
    public void invertSelection(ArrayList<Building> village) {
        ArrayList<Building> invertedList = new ArrayList<>(village);
        invertedList.removeAll(buildings);
        buildings = invertedList;
    }
}
