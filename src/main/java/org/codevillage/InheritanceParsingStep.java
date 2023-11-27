package org.codevillage;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InheritanceParsingStep extends EntityParsingStep {

  public InheritanceParsingStep(EntityParsingChain next) {
    super(next);
  }

  @Override
  public JavaEntity construct(EntityBuilder builder, CompilationUnit declaration) {
    InheritanceVisitor visitor = new InheritanceVisitor();
    visitor.visit(declaration, builder);

    if (this.next != null) {
      return this.next.construct(builder, declaration);
    } else {
      return builder.build();
    }
  }

  private static class InheritanceVisitor extends VoidVisitorAdapter<EntityBuilder> {

    @Override
    public void visit(ClassOrInterfaceDeclaration n, EntityBuilder builder) {
      super.visit(n, builder);
      // Check if it's a class and has a superclass
      if (n.isClassOrInterfaceDeclaration() && n.getExtendedTypes().isNonEmpty()) {
        String parentClass = n.getExtendedTypes().get(0).getNameAsString();
        builder.parent(parentClass);
      }
    }
  }

  public static void main(String[] args) throws IOException {
    String sourceCode = new String(Files.readAllBytes(Paths.get("path/to/YourJavaFile.java")));
    CompilationUnit compilationUnit = StaticJavaParser.parse(sourceCode);

    EntityParsingChain terminalStep = new BuildParsingFinish();
    EntityParsingChain inheritanceStep = new InheritanceParsingStep(terminalStep);
    JavaEntityBuilder builder = new JavaEntityBuilder();

    JavaEntity entity = inheritanceStep.construct(builder, compilationUnit);

    // Output the parent class name
    // System.out.println("Parent Class for " + entity.getName() + ": " +
    // entity.getParent().orElse("No Parent"));
  }
}
