package org.codevillage;

import java.util.ArrayList;

public abstract class JavaClass implements JavaEntity {
    abstract ArrayList<String> getAssociations();
    abstract ArrayList<String> getCompositions();
    abstract ArrayList<String> getRealizations();
    abstract String getParent();
}
