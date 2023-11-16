package org.codevillage;

/**
 *
 * creates instances of big house
 *
 */
public class BigHouseFactory implements ShapeFactory {
  public Shape createShape() {
    return new BigHouse();
  }
}
