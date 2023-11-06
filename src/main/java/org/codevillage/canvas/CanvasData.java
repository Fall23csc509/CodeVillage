package org.codevillage.canvas;

import java.util.ArrayList;
import java.util.Observable;

public class CanvasData extends Observable {
    private static CanvasData instance;
    private ArrayList<Shape> shapes;
    private CanvasData(){
        this.shapes = new ArrayList<Shape>();
    }

    public static CanvasData getInstance() {
        if (instance == null) {
            instance = new CanvasData();
        }
        return instance;
    }

    public void add(Shape newData){
        shapes.add(newData);
        setChanged();
        notifyObservers();
    }

    public void removeAll(){
        shapes.clear();
    }

    public void remove(Shape shape){
        shapes.remove(shape);
    }

    public ArrayList<Shape> getShapes(){
        return shapes;
    }
}