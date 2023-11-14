package org.codevillage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class NeighborhoodAbsolutePositioningLink extends ShapeChainLink {

    public NeighborhoodAbsolutePositioningLink(ShapeChain next) {
        super(next);
    }

//    Each Neighborhood shape within each NeighborhoodWrapper contains a width and a height now
//    Each NeighborhoodWrapper also contains an int entityYBound, containing the lowest y where we can start placing neighborhoods
//    Determine and set centerX and centerY positions for each NeighborhoodShape based on placing subneighborhoods below the entities.
//    Make sure that there is 10px of padding between neighborhoods

    @Override
    public void position(ArrayList<JavaEntity> entities) {
        positionHelper(ShapePositioningData.getInstance().getNeighborhoodWrapper(), 0, 0);
        if (next != null) {
            next.position(entities);
        }
    }

    public void positionHelper(NeighborhoodWrapper nw, int xOffset, int yOffset) {
        // Position current Neighborhood
        nw.getNeighborhoodShape().setCenterX(xOffset + nw.getNeighborhoodShape().getWidth() / 2);
        nw.getNeighborhoodShape().setCenterY(yOffset + nw.getNeighborhoodShape().getHeight() / 2);

        if (!nw.getNeighborhoods().isEmpty()) {
            // Calculate absolute yBound for positioning subneighborhoods under
            int yBound = yOffset + nw.getEntityYBound() + 10;
            // Account for padding
            int xBound = xOffset + 10;
            for (NeighborhoodWrapper w : nw.getNeighborhoods()) {
                // Position subneighborhood
                positionHelper(w, xBound, yBound);
                // Update yBound for next subneighborhood w/ padding
                yBound += w.getNeighborhoodShape().getHeight() + 10;
            }
        }
    }
}

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
