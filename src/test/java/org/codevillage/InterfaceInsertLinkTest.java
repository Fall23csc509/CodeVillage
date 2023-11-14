package org.codevillage;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InterfaceInsertLinkTest {
    @Test
    public void InterfaceInsertLinkTest1() {
        ArrayList<JavaEntity> entities = new ArrayList<>();
        JavaInterface test = new JavaInterface("Test", "Test", 0, new ArrayList<>());
        entities.add(test);
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
        ArrayList<String> lRealizations = new ArrayList<>();
        lRealizations.add("Test");
        JavaBaseClass l = new JavaBaseClass("L", "L", 0, new ArrayList<>(),
                lRealizations, lCompositions, new ArrayList<>(), null);
        entities.add(l);
        ArrayList<String> wCompositions = new ArrayList<>();
        wCompositions.add("A");
        wCompositions.add("B");
        ArrayList<String> wRealizations = new ArrayList<>();
        wRealizations.add("Test");
        JavaBaseClass w = new JavaBaseClass("W", "W", 0, new ArrayList<>(),
                wRealizations, wCompositions, new ArrayList<>(), null);
        entities.add(w);
        ArrayList<String> zCompositions = new ArrayList<>();
        zCompositions.add("A");
        zCompositions.add("B");
        zCompositions.add("C");
        zCompositions.add("D");
        ArrayList<String> zRealizations = new ArrayList<>();
        zRealizations.add("Test");
        JavaBaseClass z = new JavaBaseClass("Z", "Z", 0, new ArrayList<>(),
                zRealizations, zCompositions, new ArrayList<>(), null);
        entities.add(z);
        ArrayList<String> jCompositions = new ArrayList<>();
        jCompositions.add("D");
        ArrayList<String> jRealizations = new ArrayList<>();
        jRealizations.add("Test");
        JavaBaseClass j = new JavaBaseClass("J", "J", 0, new ArrayList<>(),
                jRealizations, jCompositions, new ArrayList<>(), null);
        entities.add(j);
        JavaBaseClass k = new JavaBaseClass("K", "K", 0, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null);
        entities.add(k);
        NeighborhoodGroupingLink link = new NeighborhoodGroupingLink(new InterfaceInsertLink(null));
        link.position(entities);
        System.out.println(ShapePositioningData.getInstance().getNeighborhoodWrapper());
        assertEquals("{[] K {[A] H F A X {[B] Y L W B Test {[C D] Z C}}} {[D] J D}}",
                ShapePositioningData.getInstance().getNeighborhoodWrapper().toString());
    }
}