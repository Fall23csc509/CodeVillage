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

    private int processNeighborhoods(List<NeighborhoodWrapper> neighborhoodWrapperList, CanvasData canvasData) {
        int totalEntityYBoundSize = 10; // Variable to track the total size (start with 10px padding)

        for (NeighborhoodWrapper neighborhoodWrapper : neighborhoodWrapperList) {
            // Continue if more subneighborhoods exist
            if (!neighborhoodWrapper.getNeighborhoods().isEmpty()) {
                int subneighborhoodEntityYBoundSize = processNeighborhoods(neighborhoodWrapper.getNeighborhoods(), canvasData) + 10; // Size of all subneighborhoods
                totalEntityYBoundSize += subneighborhoodEntityYBoundSize; // Keep track of the total size
            } else {
                // If no more subneighborhoods exist, use the remaining shapes as the size

                // Create PointShape list from NeighborhoodWrapper shapes
                List<PointShape> pointShapes = neighborhoodWrapper.getShapes().stream()
                        .filter(shape -> shape instanceof PointShape)
                        .map(shape -> (PointShape) shape)
                        .toList();

                int entityYBoundSize = calculateYHeight(pointShapes) + 10; // + 10px padding
                totalEntityYBoundSize += entityYBoundSize; // Keep track of the total size
            }
            neighborhoodWrapper.setEntityYBound(totalEntityYBoundSize);
        }
        return totalEntityYBoundSize; // Return the total size when done processing (this will be the total size taken up on the canvas)
    }

    private int calculateYHeight(List<PointShape> shapes) {
        if (shapes.isEmpty()) {
            return 0; // When the list is empty
        }

        int maxY = shapes.stream().mapToInt(PointShape::getY).max().orElse(0);
        int minY = shapes.stream().mapToInt(PointShape::getY).min().orElse(0);

        return maxY - minY;
    }


}
