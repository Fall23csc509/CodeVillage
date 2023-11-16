package org.codevillage;

import java.io.File;

public class SourceFileChecker implements FileChecker{
    public SourceFileChecker(){
        
    }

    @Override
    public File[] getFilesToParse(File directory) {
        assert directory.isDirectory() : "Cannot recognize directory.";
        File[] sourceFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".java"));
        return sourceFiles;
    }
    
}
