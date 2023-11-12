package org.codevillage;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setVisible(true);
    }
    public static void main(String[] args) {
        CanvasData canvasData = CanvasData.getInstance();
        Canvas canvas = new Canvas();
        canvasData.addPropertyChangeListener(canvas);

        // Create JFrame
        Main frame = new Main();
        frame.add(canvas);

        System.out.println("Hello world!");

    }
}