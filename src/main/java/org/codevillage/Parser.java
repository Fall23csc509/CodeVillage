package org.codevillage;

import java.io.File;
import java.util.ArrayList;
import java.io.IOException;

public interface Parser {
  public ArrayList<JavaEntity> parseSourceFiles(File directory) throws IOException;
}
