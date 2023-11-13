package org.codevillage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class NeighborhoodWrapper {
    ArrayList<JavaEntity> entities;
    ArrayList<NeighborhoodWrapper> neighborhoods;
    ArrayList<String> compositions;
    ArrayList<Shape> shapes;
    int entityYBound;
    Neighborhood neighborhoodShape;
    public NeighborhoodWrapper() {
        this.entities = new ArrayList<>();
        this.neighborhoods = new ArrayList<>();
        this.compositions = new ArrayList<>();
        this.shapes = new ArrayList<>();
    }

    public int getEntityYBound() {
        return entityYBound;
    }

    public Neighborhood getNeighborhoodShape() {
        return neighborhoodShape;
    }
    public void setNeighborhoodShape(Neighborhood neighborhoodShape) {
        this.neighborhoodShape = neighborhoodShape;
    }

    public void setEntityYBound(int entityYBound) {
        this.entityYBound = entityYBound;
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }
    public void addAllShapes(ArrayList<Shape> shapes) {
        this.shapes.addAll(shapes);
    }

    public void addEntity(JavaEntity e) {
        this.entities.add(e);
    }
    public void addNeighborhood(NeighborhoodWrapper n) {
        this.neighborhoods.add(n);
    }
    public ArrayList<JavaEntity> getEntities() {
        return entities;
    }
    public ArrayList<NeighborhoodWrapper> getNeighborhoods() {
        return neighborhoods;
    }
    public ArrayList<String> getCompositions() {
        return this.compositions;
    }

    public void addComposition(String s) {
        compositions.add(s);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NeighborhoodWrapper that)) return false;
        return Objects.equals(entities, that.entities) && Objects.equals(neighborhoods, that.neighborhoods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entities, neighborhoods);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("{");
        b.append("[");
        if(!compositions.isEmpty()) {
            compositions.forEach(c -> b.append(c + " "));
            b.setLength(b.length() - 1);
        }
        b.append("] ");
        if (!entities.isEmpty()) {
            entities.forEach(e -> b.append(e.getName() + " "));
        }


        if (!neighborhoods.isEmpty()) {
            neighborhoods.forEach(n -> b.append(n.toString() + " "));
        }
        b.setLength(b.length() - 1);
        b.append("}");
        return b.toString();
    }
}
