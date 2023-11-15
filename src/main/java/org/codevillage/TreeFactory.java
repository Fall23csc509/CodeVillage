package org.codevillage;

/**
 *
 * creates instances of tree
 *
 */
public class TreeFactory implements ShapeFactory {
  public Shape createShape() {
    return new Tree();
  }
}
