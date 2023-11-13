package org.codevillage;

import java.awt.*;

public class Tree extends PointShape {
    /**
     * Creates a new Tree with no coordinates
     */
    public Tree() {
        super();
    }

    /**
     * Creates a new Tree
     * @param x The starting x coordinate
     * @param y The starting y coordinate
     */
    public Tree(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        int x1 = getX();
        int y1 = getY();
        int trunkWidth = Math.min(getShapeSize() / 4, 10);

        g.setColor(new Color(139, 69, 19));
        g.fillRect(x1 + getShapeSize() / 2 - trunkWidth / 2, y1 + getShapeSize(), trunkWidth, getShapeSize());

        int leavesSize = Math.min(getShapeSize(), getShapeSize());
        g.setColor(new Color(34, 139, 34));
        g.fillOval(x1, y1, leavesSize, leavesSize);
    }
}
