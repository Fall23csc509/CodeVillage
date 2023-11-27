package org.codevillage;

import java.util.ArrayList;

public class ShapeInitLink extends ShapeChainLink {
  public ShapeInitLink(ShapeChainLink next) {
    super(next);
  }

  @Override
  public void position(ArrayList<JavaEntity> ignored) {
    ShapePositioningData shapePositioningData = ShapePositioningData.getInstance();
    NeighborhoodWrapper wrapper = shapePositioningData.getNeighborhoodWrapper();
    positionHelper(wrapper, shapePositioningData);
    next.position(ignored);
  }

  public void positionHelper(NeighborhoodWrapper nw, ShapePositioningData shapePositioningData) {
    nw.setNeighborhoodShape(new Neighborhood());
    for (JavaEntity entity : nw.getEntities()) {
      switch (entity.getType()) {
        case JAVA_BASE_CLASS: {
          nw.getShapes().add(handleJavaBaseClass(entity, shapePositioningData));
        }
        case JAVA_INTERFACE: {
          nw.getShapes().add(new Tree());
        }
        case JAVA_ABSTRACT_CLASS: {
          nw.getShapes().add(new Rock());
        }
      }

    }

    for (NeighborhoodWrapper subNW : nw.getNeighborhoods()) {
      positionHelper(subNW, shapePositioningData);
    }

  }

  private Shape handleJavaBaseClass(JavaEntity entity, ShapePositioningData shapePositioningData) {
    if (entity.getLinesOfCode() >= shapePositioningData.getAverageLOC()) {
      return new BigHouse();
    } else {
      return new SmallHouse();
    }
  }

}
