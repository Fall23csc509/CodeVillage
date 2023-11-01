package org.codevillage;

import java.util.Collection;

public class JavaBaseClass implements JavaClass {
    private String name;
    private String fullyQualifiedName;

    public JavaBaseClass(String name, String fullyQualifiedName) {
        this.name = name;
        this.fullyQualifiedName = fullyQualifiedName;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getFullyQualifiedName() {
        return this.fullyQualifiedName;
    }

    @Override
    public Collection<String> getAssociations() {
        return null;
    }

    @Override
    public JavaEntityType getType() {
        return null;
    }

    @Override
    public int getLinesOfCode() {
        return 0;
    }
}
