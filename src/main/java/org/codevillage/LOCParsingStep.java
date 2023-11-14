package org.codevillage;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class LOCParsingStep extends EntityParsingStep {

    public LOCParsingStep(EntityParsingChain next) {
        super(next);
    }

    @Override
    public JavaEntity construct(EntityBuilder entityBuilder, CompilationUnit compilationUnit) {
        LOCVisitor visitor = new LOCVisitor();
        visitor.visit(compilationUnit, entityBuilder);
        if (this.next != null) {
            return this.next.construct(entityBuilder, compilationUnit);
        } else {
            return entityBuilder.build();
        }
    }

    private static class LOCVisitor extends VoidVisitorAdapter<EntityBuilder> {
        @Override
        public void visit(ClassOrInterfaceDeclaration n, EntityBuilder entityBuilder) {
            super.visit(n, entityBuilder);
            int startLine = n.getBegin().get().line;
            int endLine = n.getEnd().get().line;
            int loc = endLine - startLine + 1;
            entityBuilder.linesOfCode(loc);
        }
    }
}
