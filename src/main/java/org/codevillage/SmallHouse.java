package org.codevillage;
import java.awt.*;

public class SmallHouse extends PointShape {

    /**
     * Creates a new SmallHouse with no coordinates
     */
    public SmallHouse() {
        super();
    }

    /**
     * Creates a new SmallHouse
     * @param x The starting x coordinate
     * @param y The starting y coordinate
     */
    public SmallHouse(int x, int y) {
        super(x,y);
    }

    @Override
    public void draw(Graphics g) {
        int x1 = getX();
        int y1 = getY();
        int x2 = getX() + getShapeSize();
        int y2 = getY() + getShapeSize();

        int width = x2 - x1;
        int height = y2 - y1;
        int houseBaseY = (int) (y1 + (0.6 * height));
        double offSet = width * 0.1;

        // House Base
        g.setColor(Color.darkGray);
        g.fillRect((int) (x1 + offSet), houseBaseY, (int) (width * 0.8), (int) (height * 0.4));

        // Roof
        Polygon triangle1 = new Polygon();
        triangle1.addPoint(x1, houseBaseY);
        triangle1.addPoint(x1 + width/2, (int) (y1 + (height * 0.2)));
        triangle1.addPoint(x2, houseBaseY);
        g.fillPolygon(triangle1);

        // Door
        int doorWidth = width / 8;
        int doorHeight = height / 5;
        int doorX = (x1 + width / 2) - (doorWidth / 2);
        int doorY = y2-doorHeight;
        g.setColor(new Color(139, 69, 19));
        g.fillRect(doorX, doorY, doorWidth, doorHeight);

        // Door knob
        int knobDiameter = doorWidth / 10;
        int knobX = doorX + doorWidth - knobDiameter - (doorWidth / 8);
        int knobY = doorY + (doorHeight / 2) - (knobDiameter / 2);
        g.setColor(Color.black);
        g.fillOval(knobX, knobY, knobDiameter, knobDiameter);
    }
}

