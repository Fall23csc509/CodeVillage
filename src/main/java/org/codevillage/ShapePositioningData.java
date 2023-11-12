package org.codevillage;

import java.util.ArrayList;
import java.util.HashSet;

public class ShapePositioningData {
    private static ShapePositioningData instance;
    private NeighborhoodWrapper neighborhoodWrapper;
    private HashSet<String> composerNames;
    private ShapePositioningData() {
        neighborhoodWrapper = null;
        composerNames = new HashSet<>();
    }
    public static ShapePositioningData getInstance() {
        if (instance == null) {
            instance = new ShapePositioningData();
        }
        return instance;
    }

    public NeighborhoodWrapper getNeighborhoodWrapper() {
        return neighborhoodWrapper;
    }

    public void setNeighborhoodWrapper(NeighborhoodWrapper neighborhoodWrapper) {
        this.neighborhoodWrapper = neighborhoodWrapper;
    }

    public HashSet<String> getComposerNames() {
        return composerNames;
    }
}
