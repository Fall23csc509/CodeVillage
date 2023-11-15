package org.codevillage;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.ArrayList;
import java.util.List;

public class CompositionParsingStep extends EntityParsingStep {

    public CompositionParsingStep(EntityParsingChain next) {
        super(next);
    }

    @Override
    public JavaEntity construct(EntityBuilder builder, CompilationUnit declaration) {
        // Parse the CompilationUnit to find composition relationships
        CompositionVisitor visitor = new CompositionVisitor();
        visitor.visit(declaration, builder);

        // Proceed with the next step in the chain, if any
        if (next != null) {
            return next.construct(builder, declaration);
        } else {
            return builder.build();
        }
    }

    private static class CompositionVisitor extends VoidVisitorAdapter<EntityBuilder> {
        @Override
        public void visit(ClassOrInterfaceDeclaration n, EntityBuilder builder) {
            super.visit(n, builder);
            n.getFields().forEach(field -> analyzeField(field, builder));
        }

        private void analyzeField(FieldDeclaration field, EntityBuilder builder) {
            field.getVariables().forEach(variable -> {
                if (variable.getType().isClassOrInterfaceType()) {
                    builder.addComposition(variable.getType().asString());
                }
            });
        }
    }

    public static void main(String[] args) {
        // Mock scenario for demonstration
        JavaEntityBuilder builder = new JavaEntityBuilder();
        builder.name("SampleEntity");
        builder.fullyQualifiedName("org.codevillage.SampleEntity");
        builder.type(JavaEntityType.JAVA_BASE_CLASS);
        builder.linesOfCode(100);

        // Sample CompilationUnit representing the Java source code
        CompilationUnit mockUnit = new CompilationUnit();
        // ... [Add mock code structure to mockUnit]

        // Using CompositionParsingStep
        CompositionParsingStep step = new CompositionParsingStep(null);
        JavaEntity entity = step.construct(builder, mockUnit);

        // Print the result
        System.out.println("Entity Name: " + entity.getName());
        System.out.println("Compositions: " + entity.getDependencies()); // Assuming compositions are added to dependencies for demonstration
    }
}
