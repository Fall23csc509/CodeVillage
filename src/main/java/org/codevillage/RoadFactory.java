package org.codevillage;

public class RoadFactory implements ShapeFactory {
  public Shape createShape() {
    return new Road();
  }
}
