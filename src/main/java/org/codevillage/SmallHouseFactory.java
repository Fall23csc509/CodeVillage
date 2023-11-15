package org.codevillage;

/**
 *
 * creates instances of small house
 *
 */
public class SmallHouseFactory implements ShapeFactory {
  public Shape createShape() {
    return new SmallHouse();
  }
}
