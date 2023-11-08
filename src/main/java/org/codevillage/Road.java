package org.codevillage;

import java.awt.*;
import java.awt.geom.Line2D;

public class Road implements Shape
{
    private static final Stroke roadStroke = new BasicStroke(40f);
    private static final float[] dashedYellowLinePattern = {50f, 60f};
    private static final Stroke dashedYellowStroke = new BasicStroke(4f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1.0f, dashedYellowLinePattern, 2.0f);
    private final Line2D roadLine;
    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;

    /**
     * Creates a new road specified by the
     * @param x1 The x component of the start point
     * @param y1 The y component of the start point
     * @param x2 The x component of the end point
     * @param y2 The y component of the end point
     */
    public Road(int x1, int y1, int x2, int y2)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.roadLine = new Line2D.Double(x1, y1, x2, y2);
    }

    /**
     * Returns the x coordinate of the center of the road
     * @return The x coordinate of the center of the road
     */
    @Override
    public int getX()
    {
        return (x1 + x2) / 2;
    }

    @Override
    public int getY()
    {
        return (y1 + y2) / 2;
    }

    @Override
    public void draw(Graphics g)
    {
        Graphics2D graphics2D = (Graphics2D) g;

        Object previousAntialiasingState = graphics2D.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setColor(Color.BLACK);
        graphics2D.setStroke(roadStroke);
        graphics2D.draw(roadLine);
        graphics2D.setColor(Color.YELLOW);
        graphics2D.setStroke(dashedYellowStroke);
        graphics2D.draw(roadLine);

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, previousAntialiasingState);
    }
}
