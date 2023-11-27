package org.codevillage;

import java.io.File;
import java.util.ArrayList;
import java.io.IOException;

public class SourceCodeParser implements Parser {
  static ArrayList<JavaEntity> entities;

  public SourceCodeParser() {
    this.entities = new ArrayList<>();
  };

  @Override
  public ArrayList<JavaEntity> parseSourceFiles(File directory) throws IOException {
    SourceFileChecker fileChecker = new SourceFileChecker();
    JavaEntityFactory entityFactory = new JavaEntityFactory();
    // validate java files as source code
    File[] sourceCodeFiles = fileChecker.getFilesToParse(directory);
    for (File file : sourceCodeFiles) {
      entities.add(entityFactory.createEntityFromFile(file));
    }
    return entities;
  }

}
