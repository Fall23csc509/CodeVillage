package org.codevillage;

import java.nio.file.Path;
import java.util.List;

public interface FileChecker {
    // Given a directory that is holding the code, return all the .java files in it that should be parsed
    List<Path> getFilesToParse(Path directory);
}