package org.codevillage.canvas;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Canvas implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        ArrayList<Shape> newData = ((CanvasData) o).getShapes();
        for(Shape shape : newData){
            shape.draw(); // Draw all shapes on canvas again
        }
    }
}
