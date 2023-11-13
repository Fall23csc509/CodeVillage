package org.codevillage;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class InheritanceParsingStep extends EntityParsingStep {

    public InheritanceParsingStep(EntityParsingChain next) {
        super(next);
    }

    @Override
    public JavaEntity construct(EntityBuilder entityBuilder, CompilationUnit compilationUnit) {
        InterfaceVisitor visitor = new InterfaceVisitor();
        visitor.visit(compilationUnit, entityBuilder);
        if (this.next != null) {
            return this.next.construct(entityBuilder, compilationUnit);
        } else {
            return entityBuilder.build();
        }
    }

    private static class InterfaceVisitor extends VoidVisitorAdapter<EntityBuilder> {
        
        @Override
        public void visit(ClassOrInterfaceDeclaration n, EntityBuilder entityBuilder) {
            super.visit(n, entityBuilder);
            n.getImplementedTypes().forEach(implementedType -> 
                entityBuilder.addRealization(implementedType.getNameAsString()));
        }
    }
}
