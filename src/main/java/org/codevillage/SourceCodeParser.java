package org.codevillage;

import java.io.File;
import java.util.ArrayList;

public class SourceCodeParser implements Parser{
    ArrayList<JavaEntity> entities;

    public SourceCodeParser(){};

    @Override
    public void parseSourceFiles(File directory) {
        SourceFileChecker fileChecker = new SourceFileChecker();
        JavaEntityFactory entityFactory = new JavaEntityFactory();
        // validate java files as source code
        File[] sourceCodeFiles = fileChecker.getFilesToParse(directory);
        for (File file : sourceCodeFiles){
            entities.add(entityFactory.createEntityFromFile(file));
        }
    }
    
}
