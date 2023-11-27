package org.codevillage;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.utils.SourceRoot;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.io.IOException;

public class TypeParsingStep extends EntityParsingStep {
    public TypeParsingStep(EntityParsingChain next) {
        super(next);
        //TODO Auto-generated constructor stub
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
}
