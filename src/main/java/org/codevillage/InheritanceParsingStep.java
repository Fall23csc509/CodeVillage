package org.codevillage;

import com.github.javaparser.ast.CompilationUnit;
import java.util.Set;
import java.util.HashSet;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class InheritanceParsingStep extends EntityParsingStep {

    public InheritanceParsingStep(EntityParsingChain next) {
        super(next);
    }

    @Override
    public JavaEntity construct(EntityBuilder builder, CompilationUnit declaration) {
        // Existing logic to parse the entity
        JavaEntity entity = next.construct(builder, declaration);

        // New logic to get associated class names
        Set<String> associatedClassNames = getAssociatedClassNames(entity);
        associatedClassNames.forEach(builder::addAssociation);

        return entity;
    }

    private Set<String> getAssociatedClassNames(Object entity) {
        Set<String> classNames = new HashSet<>();
        Class<?> entityClass = entity.getClass();

        // Inspect superclass
        Class<?> superClass = entityClass.getSuperclass();
        if (superClass != null && superClass != Object.class) {
            classNames.add(superClass.getName());
        }

        // Inspect interfaces
        Class<?>[] interfaces = entityClass.getInterfaces();
        for (Class<?> interfaceClass : interfaces) {
            classNames.add(interfaceClass.getName());
        }

        // Inspect fields
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            Class<?> fieldType = field.getType();
            classNames.add(fieldType.getName());
        }

        // Inspect methods
        Method[] methods = entityClass.getDeclaredMethods();
        for (Method method : methods) {
            Class<?> returnType = method.getReturnType();
            classNames.add(returnType.getName());
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                classNames.add(parameterType.getName());
            }
        }

        return classNames;
    }

    public static void main(String[] args) {
        // Mock setup for demonstration
        JavaEntityBuilder builder = new JavaEntityBuilder();
        builder.name("MockEntity");
        builder.fullyQualifiedName("org.codevillage.MockEntity");
        builder.type(JavaEntityType.JAVA_BASE_CLASS);

        // Mock entity class (normally, you would parse a real Java file to get a CompilationUnit)
        class MockEntity extends Object {
            // Mock fields and methods
        }

        // Setting up the parsing chain
        EntityParsingChain terminalStep = new EntityParsingFinish(); // Terminal step in the chain
        EntityParsingChain inheritanceStep = new InheritanceParsingStep(terminalStep);

        // Constructing the entity using the parsing chain
        JavaEntity entity = inheritanceStep.construct(builder, null); // Passing null for CompilationUnit for simplicity

        // Output the associated class names
        System.out.println("Associated Class Names for " + entity.getName() + ":");
        entity.getAssociations().forEach(System.out::println);
    }
}
