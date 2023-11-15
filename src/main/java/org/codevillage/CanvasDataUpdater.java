package org.codevillage;

import java.util.List;
import java.util.stream.Collectors;

public class CanvasDataUpdater {
    public static void update() {
        CanvasData canvasData = CanvasData.getInstance();
        List<Shape> shapes = canvasData.getShapes();
        int xOffset = -Camera.getInstance().getCameraX();
        int yOffset = -Camera.getInstance().getCameraY();

        List<Shape> newShapes = shapes.stream()
                .map(shape -> {
                    if (shape instanceof PointShape pointShape) {
                        pointShape.setX(pointShape.getX() + xOffset);
                        pointShape.setY(pointShape.getY() + yOffset);
                        return pointShape;
                    } else if (shape instanceof Neighborhood neighborhoodShape) {
                        neighborhoodShape.setCenterX(neighborhoodShape.getCenterX() + xOffset);
                        neighborhoodShape.setCenterY(neighborhoodShape.getCenterY() + yOffset);
                        return neighborhoodShape;
                    } else if (shape instanceof Road roadShape) {
                        roadShape.setStartX(roadShape.getStartX() + xOffset);
                        roadShape.setStartY(roadShape.getStartY() + yOffset);
                        roadShape.setEndX(roadShape.getEndX() + xOffset);
                        roadShape.setEndY(roadShape.getEndY() + yOffset);
                        return roadShape;
                    } else {
                        return shape;
                    }
                })
                .collect(Collectors.toList());

        canvasData.setShapes(newShapes);
    }
}
