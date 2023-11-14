package org.codevillage;

import java.util.ArrayList;
import java.util.List;

public class NeighborhoodDimensionsLink extends ShapeChainLink {


    public NeighborhoodDimensionsLink(ShapeChain next) {
        super(next);
    }

    @Override
    public void position(ArrayList<JavaEntity> entities) {

        processNeighborhoods(ShapePositioningData.getInstance().getNeighborhoodWrapper().getNeighborhoods(),
                CanvasData.getInstance());

        if (next != null) {
            next.position(entities);
        }
    }

    private void processNeighborhoods(List<NeighborhoodWrapper> neighborhoodWrapperList, CanvasData canvasData) {
        for (NeighborhoodWrapper neighborhoodWrapper : neighborhoodWrapperList) {

            // Get to deepest subneighborhood
            if (!neighborhoodWrapper.getNeighborhoods().isEmpty()) {
                // Go deeper if there are more subneighborhoods
                processNeighborhoods(neighborhoodWrapper.getNeighborhoods(), canvasData);
            }

            ArrayList<Integer> sizeY = new ArrayList<>();
            int size = 10; // padding 10px
            int shapeSpan = calculateShapeSpan(neighborhoodWrapper.getShapes());
            int prevEntityYBound =
            int entityYBound = prevEntityYBound + 10 * (1+  neighborhoodWrapper.getShapes().size()) + shapeSpan;
            neighborhoodWrapper.setEntityYBound(entityYBound);


        }
    }

    private int calculateShapeSpan(List<Shape> shapes) {

        for (Shape shape : shapes) {
            PointShape pointShape = (PointShape) shape;
            pointShape.getY();
        }
    }
}
