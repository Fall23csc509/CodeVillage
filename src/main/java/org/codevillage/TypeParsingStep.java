package org.codevillage;

import com.github.javaparser.ast.CompilationUnit;

public class TypeParsingStep extends EntityParsingStep{

    public TypeParsingStep(EntityParsingChain next) {
        super(next);
        //TODO Auto-generated constructor stub
    }

    @Override
    public JavaEntity construct(EntityBuilder builder, CompilationUnit declaration) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'construct'");
    }
    
}
