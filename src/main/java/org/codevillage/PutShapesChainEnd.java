package org.codevillage;

import java.util.List;

public class PutShapesChainEnd extends ShapeChainEnd {

    @Override
    public void position() {


        CanvasData canvasData = CanvasData.getInstance();
        NeighborhoodWrapper topLevelNeighborhoodWrapper = ShapePositioningData
                .getInstance()
                .getNeighborhoodWrapper();

        // Add all shapes in top level wrapper excluding the neighborhood boundary
        canvasData.addAll(topLevelNeighborhoodWrapper.getShapes());

        // Add each neighborhood and its shapes
        processNeighborhoods(topLevelNeighborhoodWrapper.getNeighborhoods());
    }

    private void processNeighborhoods(List<NeighborhoodWrapper> neighborhoodWrapperList) {
        for (NeighborhoodWrapper neighborhoodWrapper : neighborhoodWrapperList) {
            // Add neighborhood boundary and its shapes
            canvasData.add(neighborhoodWrapper.getNeighborhoodShape());
            canvasData.addAll(neighborhoodWrapper.getShapes());

            // Check if it has neighborhoods remaining
            if (neighborhoodWrapper.getNeighborhoods()) {
                // Process the remaining neighborhoods
                processNeighborhoods(neighborhoodWrapper.getNeighborhoods());
            }
        }
    }

}
