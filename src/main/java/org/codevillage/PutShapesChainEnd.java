package org.codevillage;

import java.util.ArrayList;
import java.util.List;

public class PutShapesChainEnd extends ShapeChainEnd {

    @Override
    public void position(ArrayList<JavaEntity> entities) {
        CanvasData canvasData = CanvasData.getInstance();

        NeighborhoodWrapper topLevelNeighborhoodWrapper = ShapePositioningData
                .getInstance()
                .getNeighborhoodWrapper();

        // Add all shapes in top level wrapper excluding the neighborhood boundary
        canvasData.addAll(topLevelNeighborhoodWrapper.getShapes());

        // Add each neighborhood and its shapes
        processNeighborhoods(topLevelNeighborhoodWrapper.getNeighborhoods(), canvasData);
    }

    private void processNeighborhoods(List<NeighborhoodWrapper> neighborhoodWrapperList, CanvasData canvasData) {
        for (NeighborhoodWrapper neighborhoodWrapper : neighborhoodWrapperList) {
            // Add neighborhood boundary and its shapes
            canvasData.add(neighborhoodWrapper.getNeighborhoodShape());
            canvasData.addAll(neighborhoodWrapper.getShapes());

            // Check if it has neighborhoods remaining
            if (!neighborhoodWrapper.getNeighborhoods().isEmpty()) {
                // Process the remaining neighborhoods
                processNeighborhoods(neighborhoodWrapper.getNeighborhoods(), canvasData);
            }
        }
    }

}
