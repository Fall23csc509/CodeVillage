package org.codevillage;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NeighborhoodDimensionsLinkTest {
    @Test
    public void neighborhoodDimensionsLinkTest1() {
        ArrayList<JavaEntity> entities = new ArrayList<>();
        JavaAbstractClass a = new JavaAbstractClass("A", "A", 0, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null);
        entities.add(a);
        ArrayList<String> bCompositions = new ArrayList<>();
        bCompositions.add("A");
        JavaAbstractClass b = new JavaAbstractClass("B", "B", 0, new ArrayList<>(),
                new ArrayList<>(), bCompositions, new ArrayList<>(), null);
        entities.add(b);
        JavaBaseClass x = new JavaBaseClass("x", "x", 0, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null);
        entities.add(x);
        ArrayList<String> yCompositions = new ArrayList<>();
        yCompositions.add("A");
        JavaBaseClass y = new JavaBaseClass("y", "y", 0, new ArrayList<>(),
                new ArrayList<>(), yCompositions, new ArrayList<>(), null);
        entities.add(y);
        ArrayList<String> zCompositions = new ArrayList<>();
        zCompositions.add("A");
        zCompositions.add("B");
        JavaBaseClass z = new JavaBaseClass("z", "z", 0, new ArrayList<>(),
                new ArrayList<>(), zCompositions, new ArrayList<>(), null);
        entities.add(z);
        NeighborhoodWrapper testNeighborhood = new NeighborhoodWrapper();
        testNeighborhood.addEntity(x);
        testNeighborhood.addEntity(a);
        testNeighborhood.setNeighborhoodShape(new Neighborhood());
        NeighborhoodWrapper n1 = new NeighborhoodWrapper();
        n1.addEntity(b);
        n1.addEntity(y);
        n1.setNeighborhoodShape(new Neighborhood());
        NeighborhoodWrapper n2 = new NeighborhoodWrapper();
        n2.addEntity(z);
        n2.setNeighborhoodShape(new Neighborhood());
        n1.addNeighborhood(n2);
        testNeighborhood.addNeighborhood(n1);
        ShapePositioningData.getInstance().setNeighborhoodWrapper(testNeighborhood);
        NeighborhoodDimensionsLink link = new NeighborhoodDimensionsLink(null);
        link.position(entities);
        System.out.println(ShapePositioningData.getInstance().getNeighborhoodWrapper().getNeighborhoodShape().getWidth());
        assertEquals(150,
                ShapePositioningData.getInstance().getNeighborhoodWrapper().getNeighborhoodShape().getWidth()); // Not sure what answer should be
    }
}