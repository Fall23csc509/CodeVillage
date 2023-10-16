package org.codevillage;

import java.util.Collection;
import java.util.Objects;

public class ParsedJavaClass implements JavaClass {
    private String className;
    private int numberOfAttributes;
    private String packageName;
    private int numberOfMethods;
    private Collection<String> associations;

    public ParsedJavaClass(String className, String packageName, int numberOfAttributes, int numberOfMethods, Collection<String> associations) {
        this.numberOfAttributes = numberOfAttributes;
        this.packageName = packageName;
        this.className = className;
        this.numberOfMethods = numberOfMethods;
        this.associations = associations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParsedJavaClass javaClass = (ParsedJavaClass) o;
        return numberOfAttributes == javaClass.numberOfAttributes && numberOfMethods == javaClass.numberOfMethods
                && className.equals(javaClass.className) && packageName.equals(javaClass.packageName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, numberOfAttributes, packageName, numberOfMethods);
    }

    public int getNumberOfAttributes() {
        return numberOfAttributes;
    }

    public int getNumberOfMethods() {
        return numberOfMethods;
    }

    public Collection<String> getAssociations() {
        return associations;
    }

    public String getClassName() {
        return className;
    }

    public String getPackageName() {
        return packageName;
    }
}
