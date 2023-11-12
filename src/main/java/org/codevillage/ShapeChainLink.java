package org.codevillage;

public abstract class ShapeChainLink implements ShapeChain{
    ShapeChain next;
    void setNext(ShapeChain next) {
        this.next = next;
    }
}
