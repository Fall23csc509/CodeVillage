package org.codevillage;

import com.github.javaparser.ast.CompilationUnit;

public class BuildParsingFinish extends EntityParsingFinish{
    @Override
    public JavaEntity construct(EntityBuilder builder, CompilationUnit unit) {
        return builder.build();
    }
}
