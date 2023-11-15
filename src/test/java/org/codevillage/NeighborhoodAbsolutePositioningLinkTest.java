package org.codevillage;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NeighborhoodAbsolutePositioningLinkTest {
    @Test
    void testPosition_1() {
        NeighborhoodWrapper nw = new NeighborhoodWrapper();
        Neighborhood ns = new Neighborhood();
        ns.setWidth(1000);
        ns.setHeight(2000);
        nw.setEntityYBound(120);
        nw.setNeighborhoodShape(ns);
        NeighborhoodWrapper nw1 = new NeighborhoodWrapper();
        Neighborhood ns1 = new Neighborhood();
        ns1.setWidth(500);
        ns1.setHeight(500);
        nw1.setEntityYBound(230);
        nw1.setNeighborhoodShape(ns1);
        NeighborhoodWrapper nw2 = new NeighborhoodWrapper();
        Neighborhood ns2 = new Neighborhood();
        ns2.setWidth(500);
        ns2.setHeight(500);
        nw2.setEntityYBound(120);
        nw2.setNeighborhoodShape(ns2);
        NeighborhoodWrapper nw3 = new NeighborhoodWrapper();
        Neighborhood ns3 = new Neighborhood();
        ns3.setWidth(120);
        ns3.setHeight(120);
        nw3.setEntityYBound(120);
        nw3.setNeighborhoodShape(ns3);
        nw1.addNeighborhood(nw3);
        nw.addNeighborhood(nw1);
        nw.addNeighborhood(nw2);
        ShapePositioningData.getInstance().setNeighborhoodWrapper(nw);
        NeighborhoodAbsolutePositioningLink npl = new NeighborhoodAbsolutePositioningLink(null);
        npl.position(new ArrayList<>());
        assertEquals(500, nw.getNeighborhoodShape().getCenterX());
        assertEquals(1000, nw.getNeighborhoodShape().getCenterY());
        assertEquals(260, nw1.getNeighborhoodShape().getCenterX());
        assertEquals(380, nw1.getNeighborhoodShape().getCenterY());
        assertEquals(260, nw2.getNeighborhoodShape().getCenterX());
        assertEquals(890, nw2.getNeighborhoodShape().getCenterY());
        assertEquals(80, nw3.getNeighborhoodShape().getCenterX());
        assertEquals(430, nw3.getNeighborhoodShape().getCenterY());
    }
}
