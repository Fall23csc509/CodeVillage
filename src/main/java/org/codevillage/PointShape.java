package org.codevillage;

import java.awt.*;

public class PointShape implements Shape {
    private static int SHAPE_SIZE = 100;
    private int x;
    private int y;

    /**
     * Creates a new PointShape with no coordinates
     */
    public PointShape() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Creates a new PointShape
     * @param x The starting top left x coordinate
     * @param y The starting top left y coordinate
     */
    public PointShape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static int getShapeSize() {
        return SHAPE_SIZE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void draw(Graphics g) {
        // should do nothing here
    }
}
