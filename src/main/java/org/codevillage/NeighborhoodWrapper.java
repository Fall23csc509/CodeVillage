package org.codevillage;

import java.util.ArrayList;

public class NeighborhoodWrapper {
    ArrayList<JavaEntity> entities;
    ArrayList<NeighborhoodWrapper> neighborhoods;
    public NeighborhoodWrapper(ArrayList<NeighborhoodWrapper> neighborhoods, ArrayList<JavaEntity> entities) {
        this.entities = entities;
        this.neighborhoods = neighborhoods;
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
}
