package org.codevillage;

import java.io.File;

public interface FileChecker {
    public File[] getFilesToParse(File directory);
}
