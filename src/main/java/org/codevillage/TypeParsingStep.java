package org.codevillage;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.utils.SourceRoot;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public class TypeParsingStep extends EntityParsingStep {

    public TypeParsingStep(EntityParsingChain next) {
        super(next);
    }

    @Override
    public JavaEntity construct(EntityBuilder builder, CompilationUnit declaration) {
        Optional<ClassOrInterfaceDeclaration> classOrInterface = findFirstClassOrInterface(declaration);

        if (classOrInterface.isPresent()) {
            // Determine type of Java entity
            JavaEntityType entityType = determineJavaEntityType(classOrInterface.get());
            builder.type(entityType);

            // Continue with the next step in the parsing chain
            return next != null ? next.construct(builder, declaration) : builder.build();
        }

        return null; // No relevant type declaration found
    }

    private Optional<ClassOrInterfaceDeclaration> findFirstClassOrInterface(CompilationUnit declaration) {
        return declaration.findFirst(ClassOrInterfaceDeclaration.class);
    }

    private JavaEntityType determineJavaEntityType(ClassOrInterfaceDeclaration typeDeclaration) {
        if (typeDeclaration.isInterface()) {
            return JavaEntityType.JAVA_INTERFACE;
        } else if (typeDeclaration.isAbstract()) {
            return JavaEntityType.JAVA_ABSTRACT_CLASS;
        } else {
            return JavaEntityType.JAVA_BASE_CLASS;
        }
    }

    public static void main(String[] args) {
        String filePath = "TestJavaFile.java"; // file path
        try {
            Path path = Path.of(filePath);
            SourceRoot sourceRoot = new SourceRoot(path.getParent());
            CompilationUnit cu = sourceRoot.parse("", path.getFileName().toString());

            EntityBuilder builder = new JavaEntityBuilder();
            JavaEntity entity = new TypeParsingStep(null).construct(builder, cu);

            if (entity != null) {
                System.out.println("Entity Type: " + entity.getType());
            } else {
                System.out.println("Failed to determine entity type.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
