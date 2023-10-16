package org.codevillage;

import java.io.IOException;
import java.util.List;

public interface Parser {
    List<JavaClass> parseSourceFiles() throws IOException;
}
