package org.codevillage;

import java.util.*;

public class ShapesRelativePositioningLink extends ShapeChainLink {
  public ShapesRelativePositioningLink(ShapeChainLink next) {
    super(next);
  }

  @Override
  public void position(ArrayList<JavaEntity> ignored) {
    ShapePositioningData shapePositioningData = ShapePositioningData.getInstance();
    NeighborhoodWrapper wrapper = shapePositioningData.getNeighborhoodWrapper();
    positionEntitiesInNeighborhoods(wrapper);
    next.position(ignored);
  }

  public static void positionEntitiesInNeighborhoods(NeighborhoodWrapper wrapper) {
    // base case
    if (wrapper == null)
      return;

    // We will start by organizing all the entities into 'clusters' of classes that
    // should be close
    // to each other.
    List<List<JavaEntity>> entityClusters = groupEntitiesIntoClusters(wrapper.getEntities());

    // Now that we have them in clusters, how should we lay them out?
    // The document 'Positioning_Strategy.pdf' wants:
    // - The clusters added one by one left-to-right
    // - the clusters to follow a rectangular pattern
    // - 10px of spacing between entities in the same cluster
    // - 120px of spacing between clusters
    //
    // An additional constraint we will place is to make the biggest clusters be
    // drawn on the far left
    // and to continue to draw smaller and smaller clusters as we go to the right
    entityClusters.sort(Comparator.comparingInt(List::size));
    Collections.reverse(entityClusters);
    // for simplicity's sake, lets make the rectangular layout of the entities
    // always be a
    // square (or at least the smallest possible square that can fit N entities)

    int maxEntityYBounds = -1;
    int interClusterPadding = 10;
    int shapeSideLength = PointShape.getShapeSize();
    int aggregateClusterXShift = 10;
    int xSpacingBetweenClusters = 120;
    for (List<JavaEntity> clusterEntities : entityClusters) {
      int squareLayoutSideLength = (int) Math.ceil(Math.sqrt(clusterEntities.size()));
      for (int i = 0; i < clusterEntities.size(); i++) {
        int shapeGridCoordinateX = i % squareLayoutSideLength;
        int shapeGridCoordinateY = i / squareLayoutSideLength;
        PointShape shape = (PointShape) getShapeCorrespondingToEntity(clusterEntities.get(i), wrapper);

        int shapeLeftEdgeX = aggregateClusterXShift + (shapeSideLength + interClusterPadding) * shapeGridCoordinateX;
        int shapeTopEdgeY = (shapeSideLength + interClusterPadding) * shapeGridCoordinateY + interClusterPadding;

        shape.setX(shapeLeftEdgeX + shapeSideLength / 2);
        shape.setY(shapeTopEdgeY + shapeSideLength / 2);
      }

      aggregateClusterXShift += shapeSideLength * squareLayoutSideLength +
          interClusterPadding * (squareLayoutSideLength - 1) +
          xSpacingBetweenClusters;

      maxEntityYBounds = Math.max(maxEntityYBounds,
          squareLayoutSideLength * shapeSideLength + (squareLayoutSideLength + 1) * interClusterPadding);
    }

    wrapper.setEntityYBound(maxEntityYBounds);

    // now recursively apply to all children
    for (NeighborhoodWrapper childWrapper : wrapper.getNeighborhoods()) {
      positionEntitiesInNeighborhoods(childWrapper);
    }
  }

  private static Shape getShapeCorrespondingToEntity(JavaEntity entity, NeighborhoodWrapper neighborhoodWrapper) {
    for (int i = 0; i < neighborhoodWrapper.getEntities().size(); i++) {
      if (entity.equals(neighborhoodWrapper.getEntities().get(i)))
        return neighborhoodWrapper.getShapes().get(i);
    }
    return null;
  }

  private static List<List<JavaEntity>> groupEntitiesIntoClusters(List<JavaEntity> entities) {
    // this is a set of all the entities that have not been visited by the BFS yet
    HashSet<JavaEntity> unvisitedEntities = new HashSet<>(entities);

    // this constructs the adjacency lists
    HashMap<JavaEntity, List<JavaEntity>> adjacencyGraph = new HashMap<>();
    for (JavaEntity entity1 : entities) {
      adjacencyGraph.put(entity1, new ArrayList<>());
      for (JavaEntity entity2 : entities) {
        if (entity1 == entity2)
          continue;

        if (edgeExistsBetween(entity1, entity2))
          adjacencyGraph.get(entity1).add(entity2);
      }
    }

    // this algorithm uses repeated breadth-first-search to identify all
    // the entities that belong to a cluster of related classes
    List<List<JavaEntity>> clusters = new ArrayList<>();
    while (!unvisitedEntities.isEmpty()) {
      List<JavaEntity> cluster = new ArrayList<>();
      Queue<JavaEntity> fringe = new LinkedList<>();
      fringe.add(getArbitraryElement(unvisitedEntities));
      while (!fringe.isEmpty()) {
        JavaEntity javaEntity = fringe.remove();

        // if we've already visited it, continue
        if (!unvisitedEntities.contains(javaEntity))
          continue;

        unvisitedEntities.remove(javaEntity);
        cluster.add(javaEntity);
        fringe.addAll(adjacencyGraph.get(javaEntity));
      }
      clusters.add(cluster);
    }

    return clusters;
  }

  private static <T> T getArbitraryElement(Set<T> set) {
    T arbitaryElement = null;
    for (T t : set) {
      arbitaryElement = t;
      break;
    }
    return arbitaryElement;
  }

  private static boolean edgeExistsBetween(JavaEntity entity1, JavaEntity entity2) {
    if (entity1 instanceof JavaClass && entity2 instanceof JavaInterface)
      return isImplementingInterface((JavaClass) entity1, (JavaInterface) entity2);

    else if (entity2 instanceof JavaClass && entity1 instanceof JavaInterface)
      return isImplementingInterface((JavaClass) entity2, (JavaInterface) entity1);

    else if (entity1 instanceof JavaAbstractClass && entity2 instanceof JavaAbstractClass)
      return isExtendingAbstractClass((JavaClass) entity1, (JavaAbstractClass) entity2) ||
          isExtendingAbstractClass((JavaClass) entity2, (JavaAbstractClass) entity1);

    else if (entity1 instanceof JavaBaseClass && entity2 instanceof JavaAbstractClass)
      return isExtendingAbstractClass((JavaClass) entity1, (JavaAbstractClass) entity2);

    else if (entity2 instanceof JavaBaseClass && entity1 instanceof JavaAbstractClass)
      return isExtendingAbstractClass((JavaClass) entity2, (JavaAbstractClass) entity1);

    return false;
  }

  private static boolean isImplementingInterface(JavaClass javaClass, JavaInterface javaInterface) {
    return javaClass.getRealizations().contains(javaInterface.getName());
  }

  private static boolean isExtendingAbstractClass(JavaClass javaClass, JavaAbstractClass javaAbstractClass) {
    return javaAbstractClass.getName().equals(javaClass.getParent());
  }

  public static void main(String[] args) {
    NeighborhoodWrapper wrapper = new NeighborhoodWrapper();

    JavaAbstractClass animalClass = new JavaAbstractClass("Animal", "Animal", 0,
        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), Optional.empty());

    JavaBaseClass dogClass = new JavaBaseClass("Dog", "Dog", 0,
        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), Optional.of("Animal"));

    JavaBaseClass catClass = new JavaBaseClass("Cat", "Cat", 0,
        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), Optional.of("Animal"));

    JavaAbstractClass birdClass = new JavaAbstractClass("Bird", "Bird", 0,
        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), Optional.of("Animal"));

    JavaInterface flyableInterface = new JavaInterface("Flyable", "Flyable", 0,
        new ArrayList<>());

    JavaBaseClass doveClass = new JavaBaseClass("Dove", "Dove", 0,
        new ArrayList<>(), new ArrayList<>(List.of("Flyable")), new ArrayList<>(), new ArrayList<>(),
        Optional.of("Bird"));

    JavaBaseClass penguinClass = new JavaBaseClass("Penguin", "Penguin", 0,
        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), Optional.of("Bird"));

    JavaAbstractClass vehicleClass = new JavaAbstractClass("Vehicle", "Vehicle", 0,
        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), Optional.empty());

    JavaBaseClass carClass = new JavaBaseClass("Car", "Car", 0,
        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), Optional.of("Vehicle"));

    JavaBaseClass truckClass = new JavaBaseClass("Truck", "Truck", 0,
        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), Optional.of("Vehicle"));

    wrapper.addEntity(animalClass);
    wrapper.addAllShapes(new ArrayList<>(List.of(new Rock())));

    wrapper.addEntity(dogClass);
    wrapper.addAllShapes(new ArrayList<>(List.of(new SmallHouse())));

    wrapper.addEntity(catClass);
    wrapper.addAllShapes(new ArrayList<>(List.of(new BigHouse())));

    wrapper.addEntity(birdClass);
    wrapper.addAllShapes(new ArrayList<>(List.of(new Rock())));

    wrapper.addEntity(penguinClass);
    wrapper.addAllShapes(new ArrayList<>(List.of(new SmallHouse())));

    wrapper.addEntity(doveClass);
    wrapper.addAllShapes(new ArrayList<>(List.of(new BigHouse())));

    wrapper.addEntity(flyableInterface);
    wrapper.addAllShapes(new ArrayList<>(List.of(new Tree())));

    wrapper.addEntity(vehicleClass);
    wrapper.addAllShapes(new ArrayList<>(List.of(new Rock())));

    wrapper.addEntity(carClass);
    wrapper.addAllShapes(new ArrayList<>(List.of(new SmallHouse())));

    wrapper.addEntity(truckClass);
    wrapper.addAllShapes(new ArrayList<>(List.of(new BigHouse())));

    positionEntitiesInNeighborhoods(wrapper);
    System.out.println("Done");
  }
}
