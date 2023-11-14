package org.codevillage;

import java.util.ArrayList;
import java.util.Optional;

public class JavaEntityBuilder implements EntityBuilder{
    String name, fullyQualifiedName;
    Optional<String> parent;
    ArrayList<String> dependencies, compositions, realizations, associations;
    JavaEntityType type;
    int linesOfCode;
    public JavaEntityBuilder() {
        dependencies = new ArrayList<>();
        compositions = new ArrayList<>();
        realizations = new ArrayList<>();
        associations = new ArrayList<>();
        parent = Optional.empty();
    }
    @Override
    public JavaEntity build() {
        if (this.type == JavaEntityType.JAVA_INTERFACE) {
            return new JavaInterface(name, fullyQualifiedName, linesOfCode, type);
        }
        else if (this.type == JavaEntityType.JAVA_BASE_CLASS) {
            return new JavaBaseClass(name, fullyQualifiedName, linesOfCode, dependencies,
                    realizations, compositions, associations, parent);
        }
        else if (this.type == JavaEntityType.JAVA_ABSTRACT_CLASS) {
            return new JavaAbstractClass(name, fullyQualifiedName, linesOfCode, type, dependencies,
                    realizations, compositions, parent);
        }
        else {
            return null;
        }
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
    public void type(JavaEntityType type) {
        this.type = type;
    }

    @Override
    public void addDependency(String s) {
        this.dependencies.add(s);
    }

    @Override
    public void addComposition(String s) {
        this.compositions.add(s);
    }

    @Override
    public void addRealization(String s) {
        this.realizations.add(s);
    }

    public void addAssociation(String s) {this.associations.add(s); }

    @Override
    public void parent(String s) {
        this.parent = Optional.of(s);
    }

    @Override
    public void linesOfCode(int i) {
        this.linesOfCode = i;
    }
}
