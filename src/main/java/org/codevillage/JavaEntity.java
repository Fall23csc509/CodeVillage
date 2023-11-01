package org.codevillage;

import java.util.Collection;

public interface JavaEntity {
    String getName();

    String getFullyQualifiedName();

    Collection<String> getAssociations();

    JavaEntityType getType();

    int getLinesOfCode();
}
