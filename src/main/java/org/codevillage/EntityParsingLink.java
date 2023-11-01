package org.codevillage;

import com.github.javaparser.ast.CompilationUnit;
import org.junit.jupiter.api.Test;

public abstract class EntityParsingLink implements EntityParsingChain {
    EntityParsingChain next;

    public EntityParsingLink(EntityParsingChain next) {
        this.next = next;
    }

    public abstract JavaEntity construct(EntityBuilder builder, CompilationUnit declaration);
}
