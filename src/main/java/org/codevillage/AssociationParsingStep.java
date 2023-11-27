package org.codevillage;

import com.github.javaparser.ast.CompilationUnit;

public class AssociationParsingStep extends EntityParsingStep {

  public AssociationParsingStep(EntityParsingChain next) {
    super(next);
  }

  @Override
  public JavaEntity construct(EntityBuilder builder, CompilationUnit declaration) {
    throw new UnsupportedOperationException("Unimplemented method 'construct'");
  }

}
