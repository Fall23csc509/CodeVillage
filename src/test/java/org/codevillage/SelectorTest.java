package org.codevillage;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SelectorTest {

    @Test
    void getSelector() {
        Selector s1 = Selector.getSelector();
        Selector s2 = Selector.getSelector();
        assertEquals(s1, s2);
    }

    @Test
    void getBuildings() {
        Building b1 = new Building();
        Building b2 = new Building();
        Building b3 = new Building();
        Selector.getSelector().addBuilding(b1);
        Selector.getSelector().addBuilding(b2);
        Selector.getSelector().addBuilding(b3);
        Selector.getSelector().removeBuilding(b2);
        ArrayList<Building> test = new ArrayList<>();
        test.add(b1);
        test.add(b3);
        assertEquals(Selector.getSelector().getBuildings(), test);
    }

    @Test
    void clearBuildings() {
        Building b1 = new Building();
        Building b2 = new Building();
        Building b3 = new Building();
        Selector.getSelector().addBuilding(b1);
        Selector.getSelector().addBuilding(b2);
        Selector.getSelector().addBuilding(b3);
        Selector.getSelector().clearBuildings();
        ArrayList<Building> test = new ArrayList<>();
        assertEquals(Selector.getSelector().getBuildings(), test);
    }

    @Test
    void invertSelection() {
        Building b1 = new Building();
        Building b2 = new Building();
        Building b3 = new Building();
        Building b4 = new Building();
        Building b5 = new Building();
        Building b6 = new Building();
        ArrayList<Building> village = new ArrayList<>();
        village.add(b1);
        village.add(b2);
        village.add(b3);
        village.add(b4);
        village.add(b5);
        village.add(b6);
        Selector.getSelector().addBuilding(b1);
        Selector.getSelector().addBuilding(b2);
        Selector.getSelector().addBuilding(b3);
        Selector.getSelector().invertSelection(village);
        ArrayList<Building> test = new ArrayList<>();
        test.add(b4);
        test.add(b5);
        test.add(b6);
        assertEquals(Selector.getSelector().getBuildings(), test);
    }
}