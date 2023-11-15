package org.codevillage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class JavaEntityFactory implements EntityFactory{

    @Override
    public JavaEntity createEntityFromFile(File filename) throws IOException {
        TypeParsingStep type = new TypeParsingStep(null);
        String fileContent = Files.readString(Paths.get(filename.toURI()));
        CompilationUnit compilationUnit = StaticJavaParser.parse(fileContent);
        JavaEntity entity = type.construct(new JavaEntityBuilder(), compilationUnit);
        return entity;
    }
}
