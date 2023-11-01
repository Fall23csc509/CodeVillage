package org.codevillage;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JavaEntityFactory {
    public JavaEntity createEntityFromFile(File f) {
        try {
            String fileContent = Files.readString(Paths.get(f.toURI()));
            CompilationUnit compilationUnit = StaticJavaParser.parse(fileContent);
            // TODO: Replace with a full chain
            EntityParsingChain c = new NameParsingLink(null);
            EntityBuilder b = new JavaEntityBuilder();
            JavaEntity e = c.construct(b, compilationUnit);
            return e;
        }
        catch (Exception e) {
            return null;
        }
    }
}