package org.codevillage;

import com.github.javaparser.ast.CompilationUnit;

public interface EntityParsingChain {
    JavaEntity construct(EntityBuilder builder, CompilationUnit declaration);
}
