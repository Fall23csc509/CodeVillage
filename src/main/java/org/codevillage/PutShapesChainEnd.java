package org.codevillage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
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

        convertNeighborhoodToJson(topLevelNeighborhoodWrapper, "city.json");

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

    private void convertNeighborhoodToJson(NeighborhoodWrapper neighborhoodWrapper, String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        // objectMapper.enable(SerializationFeature.INDENT);

        try {
            // Convert neighborhoodWrapper to JSON and write to a file
            objectMapper.writeValue(new File(fileName), neighborhoodWrapper);
            System.out.println("JSON data saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
