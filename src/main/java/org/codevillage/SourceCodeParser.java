package org.codevillage;

import java.io.File;

public class SourceCodeParser implements Parser{

    @Override
    public void parseSourceFiles(File directory) {
        SourceFileChecker fileChecker = new SourceFileChecker();
        // validate java files as source code
        File[] sourceCodeFiles = fileChecker.getFilesToParse(directory);
    }
    
}
