package org.codevillage;

import java.util.Collection;

public interface JavaPackage {
    public String getName();
    public Collection<JavaPackage> getSubpackages();
}
