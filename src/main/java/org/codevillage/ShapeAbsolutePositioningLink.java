package org.codevillage;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShapeAbsolutePositioningLink extends ShapeChainLink {

    public ShapeAbsolutePositioningLink(ShapeChain next) {
        super(next);
    }

    /*
            Each Shape within the NeighborhoodWrapper has an X and Y that is relative to the top-left of the neighborhood
            The Neighborhood shape stored in each NeighborhoodWrapper now has a width, height, centerX, and centerY that
            corresponds to a location on the canvas.
            For each neighborhood:
            Correctly position each entity from its relative position
            Correctly position the entities in its subneighborhoods
         */
    @Override
    public void position(ArrayList<JavaEntity> entities) {
        positionHelper(ShapePositioningData.getInstance().getNeighborhoodWrapper());
        if (next != null) {
            next.position(entities);
        }
    }

    public void positionHelper(NeighborhoodWrapper nw) {
        int xOffset = 0, yOffset = 0;
        Neighborhood n;
        if ((n = nw.getNeighborhoodShape()) != null) {
            // Make sure to add the sizes to the offsets
            xOffset = n.getCenterX() - n.getWidth() / 2;
            yOffset = n.getCenterY() - n.getHeight() / 2;
            for (Shape s : nw.getShapes()) {
                // TODO: fix this by changing definition of NeighborhoodWrapper
                PointShape s1 = (PointShape) s;
                s1.setX(s1.getX() + xOffset);
                s1.setY(s1.getY() + yOffset);
            }
        }
        for (NeighborhoodWrapper subNw : nw.getNeighborhoods()) {
            positionHelper(subNw);
        }
    }
}

class ShapeAbsolutePositioningLinkTest {

   @Test
    void testPosition_1() {
        NeighborhoodWrapper nw = new NeighborhoodWrapper();
        nw.addAllShapes(new ArrayList<Shape>() {{
            add(new Tree());
            add(new Tree());
            add(new Tree());
        }});

       ((PointShape) nw.getShapes().get(0)).setX(10);
       ((PointShape) nw.getShapes().get(0)).setY(10);

        ((PointShape) nw.getShapes().get(1)).setX(120);
        ((PointShape) nw.getShapes().get(1)).setY(10);

        ((PointShape) nw.getShapes().get(2)).setX(230);
        ((PointShape) nw.getShapes().get(2)).setY(10);

        NeighborhoodWrapper nw1 = new NeighborhoodWrapper();
        nw1.addAllShapes(new ArrayList<Shape>() {{
            add(new Tree());
            add(new Tree());
            add(new Tree());
        }});

        ((PointShape) nw1.getShapes().get(0)).setX(10);
        ((PointShape) nw1.getShapes().get(0)).setY(10);

        ((PointShape) nw1.getShapes().get(1)).setX(120);
        ((PointShape) nw1.getShapes().get(1)).setY(10);

        ((PointShape) nw1.getShapes().get(2)).setX(230);
        ((PointShape) nw1.getShapes().get(2)).setY(10);


        Neighborhood ns1 = new Neighborhood();
        ns1.setWidth(340);
        ns1.setHeight(120);
        ns1.setCenterX(180);
        ns1.setCenterY(190);

        nw1.setNeighborhoodShape(ns1);

        nw.addNeighborhood(nw1);

        ShapePositioningData.getInstance().setNeighborhoodWrapper(nw);

        ShapeAbsolutePositioningLink sapl = new ShapeAbsolutePositioningLink(null);
        sapl.position(null);
        assertEquals(((PointShape) nw.getShapes().get(0)).getX(), 10);
        assertEquals(((PointShape) nw.getShapes().get(0)).getY(), 10);

       assertEquals(((PointShape) nw1.getShapes().get(0)).getX(), 20);
        assertEquals(((PointShape) nw1.getShapes().get(0)).getY(), 140);
    }

}