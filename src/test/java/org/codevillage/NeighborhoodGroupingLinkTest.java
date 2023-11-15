package org.codevillage;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NeighborhoodGroupingLinkTest {
    @Test
    public void neighborhoodGroupingTest1() {
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
        NeighborhoodWrapper n1 = new NeighborhoodWrapper();
        n1.addEntity(b);
        n1.addEntity(y);
        NeighborhoodWrapper n2 = new NeighborhoodWrapper();
        n2.addEntity(z);
        n1.addNeighborhood(n2);
        testNeighborhood.addNeighborhood(n1);
        NeighborhoodGroupingLink link = new NeighborhoodGroupingLink(null);
        link.position(entities);
        assertEquals("{[] x {[A] y A {[B] z B}}}",
                ShapePositioningData.getInstance().getNeighborhoodWrapper().toString());
    }
    @Test
    public void neighborhoodGroupingTest2() {
        ArrayList<JavaEntity> entities = new ArrayList<>();
        JavaAbstractClass a = new JavaAbstractClass("A", "A", 0, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null);
        entities.add(a);
        JavaAbstractClass b = new JavaAbstractClass("B", "B", 0, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null);
        entities.add(b);
        JavaAbstractClass c = new JavaAbstractClass("C", "C", 0, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null);
        entities.add(c);
        JavaAbstractClass d = new JavaAbstractClass("D", "D", 0, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null);
        entities.add(d);
        ArrayList<String> xCompositions = new ArrayList<>();
        xCompositions.add("A");
        xCompositions.add("D");
        JavaBaseClass x = new JavaBaseClass("X", "X", 0, new ArrayList<>(),
                new ArrayList<>(), xCompositions, new ArrayList<>(), null);
        entities.add(x);
        ArrayList<String> hCompositions = new ArrayList<>();
        hCompositions.add("A");
        JavaBaseClass h = new JavaBaseClass("H", "H", 0, new ArrayList<>(),
                new ArrayList<>(), hCompositions, new ArrayList<>(), null);
        entities.add(h);
        ArrayList<String> fCompositions = new ArrayList<>();
        fCompositions.add("A");
        JavaBaseClass f = new JavaBaseClass("F", "F", 0, new ArrayList<>(),
                new ArrayList<>(), fCompositions, new ArrayList<>(), null);
        entities.add(f);
        ArrayList<String> yCompositions = new ArrayList<>();
        yCompositions.add("A");
        yCompositions.add("B");
        JavaBaseClass y = new JavaBaseClass("Y", "Y", 0, new ArrayList<>(),
                new ArrayList<>(), yCompositions, new ArrayList<>(), null);
        entities.add(y);
        ArrayList<String> lCompositions = new ArrayList<>();
        lCompositions.add("A");
        lCompositions.add("B");
        JavaBaseClass l = new JavaBaseClass("L", "L", 0, new ArrayList<>(),
                new ArrayList<>(), lCompositions, new ArrayList<>(), null);
        entities.add(l);
        ArrayList<String> wCompositions = new ArrayList<>();
        wCompositions.add("A");
        wCompositions.add("B");
        JavaBaseClass w = new JavaBaseClass("W", "W", 0, new ArrayList<>(),
                new ArrayList<>(), wCompositions, new ArrayList<>(), null);
        entities.add(w);
        ArrayList<String> zCompositions = new ArrayList<>();
        zCompositions.add("A");
        zCompositions.add("B");
        zCompositions.add("C");
        zCompositions.add("D");
        JavaBaseClass z = new JavaBaseClass("Z", "Z", 0, new ArrayList<>(),
                new ArrayList<>(), zCompositions, new ArrayList<>(), null);
        entities.add(z);
        ArrayList<String> jCompositions = new ArrayList<>();
        jCompositions.add("D");
        JavaBaseClass j = new JavaBaseClass("J", "J", 0, new ArrayList<>(),
                new ArrayList<>(), jCompositions, new ArrayList<>(), null);
        entities.add(j);
        JavaBaseClass k = new JavaBaseClass("K", "K", 0, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null);
        entities.add(k);
        NeighborhoodGroupingLink link = new NeighborhoodGroupingLink(null);
        link.position(entities);
        System.out.println(ShapePositioningData.getInstance().getNeighborhoodWrapper());
        assertEquals("{[] K {[A] H F A X {[B] Y L W B {[C D] Z C}}} {[D] J D}}",
                ShapePositioningData.getInstance().getNeighborhoodWrapper().toString());
    }
    @Test
    public void neighborhoodGroupingTest3() {
        ArrayList<JavaEntity> entities = new ArrayList<>();
        JavaInterface javaInterface = new JavaInterface("javaInterface", "javaInterface", 0, new ArrayList<>());
        entities.add(javaInterface);
        NeighborhoodGroupingLink link = new NeighborhoodGroupingLink(null);
        link.position(entities);
        assertEquals("{[]}",
                ShapePositioningData.getInstance().getNeighborhoodWrapper().toString());
    }
}