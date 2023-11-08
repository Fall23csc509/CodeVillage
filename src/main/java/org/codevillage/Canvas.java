package org.codevillage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class Canvas implements PropertyChangeListener {
    public Canvas()
    {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("shapes".equals(evt.getPropertyName())) {
            List<Shape> newShapes = (List<Shape>) evt.getNewValue();
            System.out.println("Canvas has been updated with new shapes:");
            for (Shape shape : newShapes) {
                System.out.println(shape);
            }
        }
    }


}
