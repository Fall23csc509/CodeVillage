package org.codevillage;

import java.util.ArrayList;

public class JavaInterface implements JavaEntity{
    String name, fullyQualifiedName;
    int linesOfCode;
    ArrayList<String> dependencies;
    public JavaInterface(String name, String fullyQualifiedName, int linesOfCode, ArrayList<String> dependencies) {
        this.name = name;
        this.fullyQualifiedName = fullyQualifiedName;
        this.linesOfCode = linesOfCode;
        this.dependencies = dependencies;
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
    public ArrayList<String> getDependencies() {
        return this.dependencies;
    }

    @Override
    public int getLinesOfCode() {
        return this.linesOfCode;
    }

    @Override
    public JavaEntityType getType() {
        return JavaEntityType.JAVA_INTERFACE;
    }
}
