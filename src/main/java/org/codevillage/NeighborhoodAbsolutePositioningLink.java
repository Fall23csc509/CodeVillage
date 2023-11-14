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

