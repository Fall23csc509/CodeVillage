package org.codevillage;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NeighborhoodGroupingLinkTest {
    @Test
    public void neighborhoodGroupingTest1() {
        ArrayList<JavaEntity> entities = new ArrayList<>();
        JavaAbstractClass a = new JavaAbstractClass("A");
        entities.add(a);
        JavaAbstractClass b = new JavaAbstractClass("B");
        b.addComposition("A");
        entities.add(b);
        JavaBaseClass x = new JavaBaseClass("x");
        entities.add(x);
        JavaBaseClass y = new JavaBaseClass("y");
        y.addComposition("A");
        entities.add(y);
        JavaBaseClass z = new JavaBaseClass("z");
        z.addComposition("A");
        z.addComposition("B");
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
        JavaAbstractClass a = new JavaAbstractClass("A");
        entities.add(a);
        JavaAbstractClass b = new JavaAbstractClass("B");
        entities.add(b);
        JavaAbstractClass c = new JavaAbstractClass("C");
        entities.add(c);
        JavaAbstractClass d = new JavaAbstractClass("D");
        entities.add(d);
        JavaBaseClass x = new JavaBaseClass("X");
        x.addComposition("A");
        x.addComposition("D");
        entities.add(x);
        JavaBaseClass h = new JavaBaseClass("H");
        h.addComposition("A");
        entities.add(h);
        JavaBaseClass f = new JavaBaseClass("F");
        f.addComposition("A");
        entities.add(f);
        JavaBaseClass y = new JavaBaseClass("Y");
        y.addComposition("A");
        y.addComposition("B");
        entities.add(y);
        JavaBaseClass l = new JavaBaseClass("L");
        l.addComposition("A");
        l.addComposition("B");
        entities.add(l);
        JavaBaseClass w = new JavaBaseClass("W");
        w.addComposition("A");
        w.addComposition("B");
        entities.add(w);
        JavaBaseClass z = new JavaBaseClass("Z");
        z.addComposition("A");
        z.addComposition("B");
        z.addComposition("C");
        z.addComposition("D");
        entities.add(z);
        JavaBaseClass j = new JavaBaseClass("J");
        j.addComposition("D");
        entities.add(j);
        JavaBaseClass k = new JavaBaseClass("K");
        entities.add(k);
        NeighborhoodGroupingLink link = new NeighborhoodGroupingLink(null);
        link.position(entities);
        System.out.println(ShapePositioningData.getInstance().getNeighborhoodWrapper());
        assertEquals("{[] K {[A] H F A X {[B] Y L W B {[C D] Z C}}} {[D] J D}}",
                ShapePositioningData.getInstance().getNeighborhoodWrapper().toString());
    }
}