package org.codevillage;

import java.awt.*;
import java.awt.geom.Line2D;

public class Road implements Shape
{
    private static final Stroke roadStroke = new BasicStroke(40f);
    private static final float[] dashedYellowLinePattern = {50f, 60f};
    private static final Stroke dashedYellowStroke = new BasicStroke(4f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1.0f, dashedYellowLinePattern, 2.0f);

    private int startX;
    private int startY;
    private int endX;
    private int endY;

    public Road()
    {
        this.startX = 0;
        this.startY = 0;
        this.endX = 0;
        this.endY = 0;
    }

    /**
     * Creates a new road specified by the
     * @param startX The x component of the start point
     * @param startY The y component of the start point
     * @param endX The x component of the end point
     * @param endY The y component of the end point
     */
    public Road(int startX, int startY, int endX, int endY)
    {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }


    @Override
    public void draw(Graphics g)
    {
        Graphics2D graphics2D = (Graphics2D) g;

        Object previousAntialiasingState = graphics2D.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Line2D roadLine = new Line2D.Double(startX, startY, endX, endY);

        graphics2D.setColor(Color.BLACK);
        graphics2D.setStroke(roadStroke);
        graphics2D.draw(roadLine);
        graphics2D.setColor(Color.YELLOW);
        graphics2D.setStroke(dashedYellowStroke);
        graphics2D.draw(roadLine);

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, previousAntialiasingState);
    }

    public void setStartX(int startX)
    {
        this.startX = startX;
    }

    public void setStartY(int startY)
    {
        this.startY = startY;
    }

    public void setEndX(int endX)
    {
        this.endX = endX;
    }

    public void setEndY(int endY)
    {
        this.endY = endY;
    }
    public int getStartX()
    {
        return startX;
    }

    public int getStartY()
    {
        return startY;
    }

    public int getEndX()
    {
        return endX;
    }

    public int getEndY()
    {
        return endY;
    }

}
