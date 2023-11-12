package org.codevillage;

import java.util.List;

public class PutShapesChainEnd extends ShapeChainEnd{
    @Override
    public void construct() {
        List<Shape> canvasData = CanvasData.getInstance().getShapes();
        List<Shape> shapePositioningData = ShapePositioningData.getInstance().getShapes();
        canvasData.addAll(shapePositioningData);
    }

}
