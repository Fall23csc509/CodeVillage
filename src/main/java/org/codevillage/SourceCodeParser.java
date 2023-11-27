package org.codevillage;

import java.io.File;
import java.io.IOException;
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
      JavaEntity entity = entityFactory.createEntityFromFile(file);
      if (entity != null) {
        entities.add(entity);
      }
      else {
        System.out.println(file.getName() + " is not a valid java file");
      }
    }
    return entities;
  }

}
