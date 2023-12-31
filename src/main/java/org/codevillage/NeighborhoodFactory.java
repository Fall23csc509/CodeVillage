package org.codevillage;

public class NeighborhoodFactory implements ShapeFactory {
  public Shape createShape() {
    return new Neighborhood();
  }

  public static Neighborhood fromTopLeftCorner(int left, int top, int width, int height) {
    int centerX = left + width / 2;
    int centerY = top + height / 2;
    return new Neighborhood(centerX, centerY, width, height);
  }
}
