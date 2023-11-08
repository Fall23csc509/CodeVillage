package org.codevillage;

import java.awt.*;

public class Neighborhood implements Shape
{
    private static final Color COLOR = new Color(24, 242, 235, 128);
    private int top;
    private int bottom;
    private int left;
    private int right;

    public Neighborhood(int x1, int y1, int x2, int y2)
    {
        this.left = x1;
        this.top = y1;
        this.right = x2;
        this.bottom = y2;
    }

    @Override
    public int getX()
    {
        return (left + right) / 2;
    }

    @Override
    public int getY()
    {
        return (top + bottom) / 2;
    }

    @Override
    public void draw(Graphics g)
    {
        Graphics2D graphics2D = (Graphics2D) g;

        Object previousAntialiasingState = graphics2D.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(COLOR);
        graphics2D.fillRect(left, top, right - left, bottom - top);

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, previousAntialiasingState);
    }
}
