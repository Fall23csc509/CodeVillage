package org.codevillage;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;


public class Canvas extends JPanel implements PropertyChangeListener {
    public Canvas()
    {
        super(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        List<Shape> newShapes = CanvasData.getInstance().getShapes();

        for (Shape shape : newShapes) {
            shape.draw(g);
        }

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("shapes".equals(evt.getPropertyName())) {
            List<Shape> newShapes = (List<Shape>) evt.getNewValue();
            System.out.println("Canvas has been updated with new shapes:");
            for (Shape shape : newShapes) {
                System.out.println(shape);
            }
            repaint();
        }
    }


}
