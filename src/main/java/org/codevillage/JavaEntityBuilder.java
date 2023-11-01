package org.codevillage;

import java.util.Collection;

public class JavaEntityBuilder implements EntityBuilder {

    private String name;
    private String fullyQualifiedName;

    @Override
    public JavaEntity build() {
        return new JavaBaseClass(this.name, this.fullyQualifiedName);
    }

    @Override
    public void name(String s) {
        this.name = s;
    }

    @Override
    public void fullyQualifiedName(String s) {
        this.fullyQualifiedName = s;
    }

    @Override
    public void type(JavaEntityType t) {

    }

    @Override
    public void addDependency(String s) {

    }

    @Override
    public void addComposition(String s) {

    }

    @Override
    public void addRealization(String s) {

    }

    @Override
    public void parent(String s) {

    }

    @Override
    public void linesOfCode(int i) {

    }
}
