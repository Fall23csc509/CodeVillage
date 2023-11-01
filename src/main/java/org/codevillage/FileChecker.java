package org.codevillage;

import java.io.File;
import java.util.List;

public interface FileChecker {
    // Given a directory that is holding the code, return all the .java files in it that should be parsed
    List<File> getFilesToParse(File directory);
}
