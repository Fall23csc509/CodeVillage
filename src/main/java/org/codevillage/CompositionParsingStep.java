package org.codevillage;

import com.github.javaparser.ast.CompilationUnit;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CompositionParsingStep extends EntityParsingStep {

    public CompositionParsingStep(EntityParsingChain next) {
        super(next);
    }

    @Override
    public JavaEntity construct(EntityBuilder builder, CompilationUnit declaration) {
        // Construct the entity using the builder
        JavaEntity entity = builder.build();

        // Get the compositions using reflection
        List<String> compositions = getCompositionClassNames(entity);
        for (String composition : compositions) {
            builder.addComposition(composition);
        }

        // Proceed with the next step in the chain, if any
        if (next != null) {
            return next.construct(builder, declaration);
        }
        return entity;
    }

    private List<String> getCompositionClassNames(JavaEntity entity) {
        List<String> classNames = new ArrayList<>();
        Class<?> entityClass = entity.getClass();

        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            Class<?> fieldType = field.getType();
            if (!fieldType.isPrimitive() && !fieldType.equals(String.class)) {
                classNames.add(fieldType.getSimpleName());
            }
        }

        return classNames;
    }

    public static void main(String[] args) {
        // Mock scenario for demonstration
        JavaEntityBuilder builder = new JavaEntityBuilder();
        builder.name("SampleEntity");
        builder.fullyQualifiedName("org.codevillage.SampleEntity");
        builder.type(JavaEntityType.JAVA_BASE_CLASS);
        builder.linesOfCode(100);

        // Assume that CompilationUnit is not required for this example
        CompilationUnit mockUnit = null;

        // Using CompositionParsingStep
        CompositionParsingStep step = new CompositionParsingStep(null);
        JavaEntity entity = new SampleEntity(); // Mock entity for demonstration
        List<String> compositions = step.getCompositionClassNames(entity);

        // Print the result
        System.out.println("Entity Name: " + entity.getName());
        System.out.println("Compositions: " + compositions);
    }
}

// Mock JavaEntity implementation for the demonstration
class SampleEntity implements JavaEntity {
    private String name;
    private String fullyQualifiedName;
    private ArrayList<String> dependencies;
    private int linesOfCode;
    private JavaEntityType type;
    // Composition with another class
    private Address address; // Example of a composed object

    // Constructor and other methods as required
    // Implementation of JavaEntity interface methods
}

// Example of a composed class
class Address {
    String street;
    String city;
    String state;
}
