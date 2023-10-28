package org.codevillage.canvas;

public class Main {
    public static void main(String[] args){
        CanvasData data = CanvasData.getInstance();
        Canvas view = new Canvas();
        data.addObserver(view);
    }
}
