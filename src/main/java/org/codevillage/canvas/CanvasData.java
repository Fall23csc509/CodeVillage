package org.codevillage.canvas;

import java.util.ArrayList;
import java.util.Observable;

public class CanvasData extends Observable {
    private static CanvasData instance;
    private ArrayList<Shape> data;
    private CanvasData(){
        data = new ArrayList<Shape>();
    }

    public static CanvasData getInstance() {
        if (instance == null) {
            instance = new CanvasData();
        }
        return instance;
    }

    public void add(Shape newData){
        data.add(newData);
        setChanged();
        notifyObservers();
    }

    public ArrayList<Shape> getData(){
        return data;
    }
}
