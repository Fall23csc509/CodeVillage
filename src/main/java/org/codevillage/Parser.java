package org.codevillage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface Parser {
    List<JavaClass> parseSourceFiles(File directory) throws IOException;
}
