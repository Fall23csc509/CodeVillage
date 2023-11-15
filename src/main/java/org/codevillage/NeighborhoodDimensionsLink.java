package org.codevillage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NeighborhoodDimensionsLink extends ShapeChainLink {


    public NeighborhoodDimensionsLink(ShapeChain next) {
        super(next);
    }

    @Override
    public void position(ArrayList<JavaEntity> entities) {

        processNeighborhoods(ShapePositioningData.getInstance().getNeighborhoodWrapper());

        if (next != null) {
            next.position(entities);
        }
    }

    private void processNeighborhoods(NeighborhoodWrapper neighborhoodWrapper) {
        for (NeighborhoodWrapper subNeighborhoodWrapper : neighborhoodWrapper.getNeighborhoods()) {
            processNeighborhoods(subNeighborhoodWrapper);
        }
        int height = calculateHeight(neighborhoodWrapper);
        int width = calculateWidth(neighborhoodWrapper);
        neighborhoodWrapper.getNeighborhoodShape().setHeight(height);
        neighborhoodWrapper.getNeighborhoodShape().setWidth(width);
    }

    private int calculateHeight(NeighborhoodWrapper neighborhoodWrapper) {
        int padding = 10;
        int entityYBound = neighborhoodWrapper.getEntityYBound();

        if (neighborhoodWrapper.getNeighborhoods().isEmpty()) {
            return entityYBound;
        } else {
            int subNeighborhoodHeight = neighborhoodWrapper.getNeighborhoods()
                    .stream()
                    .map(NeighborhoodWrapper::getNeighborhoodShape)
                    .mapToInt(Neighborhood::getHeight)
                    .map(height -> height + padding)
                    .sum();

            return entityYBound + padding + subNeighborhoodHeight;
        }
    }

    private int calculateWidth(NeighborhoodWrapper neighborhoodWrapper) {
        List<Integer> widthList = new ArrayList<Integer>();

        // Get
        if (!neighborhoodWrapper.getNeighborhoods().isEmpty()) {
            int maxSubneighborhoodWidth = neighborhoodWrapper.getNeighborhoods()
                    .stream()
                    .map(NeighborhoodWrapper::getNeighborhoodShape)
                    .mapToInt(Neighborhood::getWidth)
                    .max()
                    .orElse(0);
            widthList.add(maxSubneighborhoodWidth + 20);
        }


        int rightMostShapeX = neighborhoodWrapper.getShapes()
                .stream()
                .filter(shape -> shape instanceof PointShape)
                .map(shape -> (PointShape) shape)
                .mapToInt(PointShape::getX)
                .max()
                .orElse(0);

        widthList.add(rightMostShapeX + 100 + 10);
        return Collections.max(widthList);

    }

}
