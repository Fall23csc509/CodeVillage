package org.codevillage;

import java.awt.*;

public class Neighborhood implements Shape
{
    private static final Color COLOR = new Color(24, 242, 235, 128);
    private int centerX;
    private int centerY;
    private int width;
    private int height;

    public Neighborhood()
    {
        this.centerX = 0;
        this.centerY = 0;
        this.width = 0;
        this.height = 0;
    }

    public Neighborhood(int centerX, int centerY, int width, int height)
    {
        this.centerX = centerX;
        this.centerY = centerY;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g)
    {
        Graphics2D graphics2D = (Graphics2D) g;

        Object previousAntialiasingState = graphics2D.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(COLOR);
        graphics2D.fillRect(centerX - width / 2, centerY - height / 2,
                centerX + width / 2, centerY + height / 2);

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, previousAntialiasingState);
    }

    public int getCenterX()
    {
        return centerX;
    }

    public void setCenterX(int centerX)
    {
        this.centerX = centerX;
    }

    public int getCenterY()
    {
        return centerY;
    }

    public void setCenterY(int centerY)
    {
        this.centerY = centerY;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }
}
