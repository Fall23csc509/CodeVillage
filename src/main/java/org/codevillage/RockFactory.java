package org.codevillage;

/**
 *
 * creates instances of rock
 *
 */
public class RockFactory implements ShapeFactory {
  public Shape createShape() {
    return new Rock();
  }
}
