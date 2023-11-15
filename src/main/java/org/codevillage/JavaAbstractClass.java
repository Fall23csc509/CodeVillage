package org.codevillage;

import java.util.ArrayList;
import java.util.Optional;

public class JavaAbstractClass extends JavaClass{
    public JavaAbstractClass(String name, String fullyQualifiedName, int linesOfCode,
                             ArrayList<String> dependencies, ArrayList<String> realizations,
                             ArrayList<String> compositions, ArrayList<String> associations,
                             Optional<String> parent) {
        super(name, fullyQualifiedName, linesOfCode, dependencies, realizations, compositions, associations, parent);
    }
    @Override
    public JavaEntityType getType() {
        return JavaEntityType.JAVA_ABSTRACT_CLASS;
    }
}
