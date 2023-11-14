package org.codevillage;

import java.util.ArrayList;

public interface JavaEntity {
    String getName();
    String getFullyQualifiedName();
    ArrayList<String> getDependencies();
    int getLinesOfCode();
    JavaEntityType getType();
}
