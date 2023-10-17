package org.codevillage;

import java.io.File;
import java.util.Collection;

public interface PackageFactory {
    public Collection<JavaPackage> createPackageFromDirectory(File directory);
}
