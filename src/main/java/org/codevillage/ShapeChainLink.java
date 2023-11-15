package org.codevillage;

public abstract class ShapeChainLink implements ShapeChain {
    ShapeChain next;

    public ShapeChainLink(ShapeChain next) {
        this.next = next;
    }
}
