package org.codevillage;

import java.awt.*;

public class Rock extends PointShape {

    /**
     * Creates a new Rock with no coordinates
     */
    public Rock() {
        super();
    }

    /**
     * Creates a new Rock
     * @param x The starting x coordinate
     * @param y The starting y coordinate
     */
    public Rock(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        int x1 = getX();
        int y1 = getY();
        int diameter = Math.min(getShapeSize(), getShapeSize());

        int centerX = x1 + getShapeSize()/2;
        int centerY = y1 + getShapeSize()/2;

        g.setColor(Color.gray);
        g.fillOval(centerX - diameter / 2, centerY - diameter / 2, diameter, diameter);
    }
}

