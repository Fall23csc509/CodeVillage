package org.codevillage;
import java.awt.*;

public class BigHouse extends PointShape {

    /**
     * Creates a new BigHouse with no coordinates
     */
    public BigHouse() {
        super();
    }

    /**
     * Creates a new BigHouse
     * @param x The starting top left x coordinate
     * @param y The starting top left y coordinate
     */
    public BigHouse(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        int x1 = getX();
        int y1 = getY();

        int x2 = getX() + getShapeSize();
        int y2 = getY() + getShapeSize();

        int width = x2 - x1;
        int height = y2 - y1;
        int houseBaseY = (int) (y1 + (0.4 * height));
        int roofOffset = 0;

        // Main body of the house
        g.setColor(Color.darkGray);
        g.fillRect(x1, houseBaseY, width, (int) (height * 0.6));

        // Roof
        Polygon triangle1 = new Polygon();
        triangle1.addPoint(x1 - roofOffset, houseBaseY);
        triangle1.addPoint(x1 + width/4, y1);
        triangle1.addPoint(x1 + width/2, houseBaseY);
        g.fillPolygon(triangle1);

        Polygon triangle2 = new Polygon();
        triangle2.addPoint(x1 + width/2, houseBaseY);
        triangle2.addPoint(x1 + 3*width/4, y1);
        triangle2.addPoint(x2 + roofOffset, houseBaseY);
        g.fillPolygon(triangle2);

        // Chimney
        int chimneyWidth = width / 10;
        int chimneyHeight = height / 8;
        int chimneyX = x1 + 3 * width / 4 - chimneyWidth / 2;
        int chimneyY = y1 + height / 8;
        g.setColor(new Color(139, 0, 0));
        g.fillRect(chimneyX, chimneyY, chimneyWidth, chimneyHeight);

        // Window
        g.setColor(Color.gray);
        int windowWidth = width / 6;
        int windowHeight = height / 10;
        int windowY = (int) (houseBaseY + ((y2 - houseBaseY) * 0.1));
        int windowOffset = windowWidth;
        // Left window
        g.fillRect((x1 + width / 2) - windowWidth - windowOffset, windowY, windowWidth, windowHeight);
        // Right window
        g.fillRect((x1 + width / 2) + windowOffset, windowY, windowWidth, windowHeight);

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

        // Living room window to fit evenly between the door end and x2
        g.setColor(Color.gray);
        int availableSpace = x2 - (doorX + doorWidth);
        int lrWindowWidth = width / 4;
        int lrWindowHeight = height / 10;
        int lrWindowX = doorX + doorWidth + (availableSpace - lrWindowWidth) / 2;
        int lrWindowY = doorY + (doorHeight / 2) - (lrWindowHeight / 2);
        g.fillRect(lrWindowX, lrWindowY, lrWindowWidth, lrWindowHeight);
    }
}
