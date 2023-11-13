package org.codevillage;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class CanvasData extends PropertyChangeSupport {
    private static CanvasData instance;
    private List<Shape> shapes;

    private CanvasData() {
        super(new Object());
        this.shapes = new ArrayList<Shape>();
    }

    public static CanvasData getInstance() {
        if (instance == null) {
            instance = new CanvasData();
        }
        return instance;
    }

    public void add(Shape newData) {
        shapes.add(newData);
        firePropertyChange("shapes", null, shapes);
    }

    public void addAll(List<Shape> newData) {
        shapes.addAll(newData);
        firePropertyChange("shapes", null, shapes);
    }

    public void removeAll() {
        shapes.clear();
        firePropertyChange("shapes", null, shapes);
    }

    public void remove(Shape shape) {
        shapes.remove(shape);
        firePropertyChange("shapes", null, shapes);
    }

    public List<Shape> getShapes() {
        return shapes;
    }
}
