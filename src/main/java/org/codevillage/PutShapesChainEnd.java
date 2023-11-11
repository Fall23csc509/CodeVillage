package org.codevillage;

public class PutShapesChainEnd extends ShapeChainEnd{
    @Override
    public void construct() {
        CanvasData canvasData = CanvasData.getInstance().getShapes();
        ShapePositioningData shapePositioningData = ShapePositioningData.getInstance().getShapes();
        canvasData.addAll(shapePositioningData);
    }

}
