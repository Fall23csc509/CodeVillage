import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class EntityAssociationsAnalyzer {

    public static Set<String> getAssociatedClassNames(Object entity) {
        Set<String> classNames = new HashSet<>();
        Class<?> entityClass = entity.getClass();

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
        // Example usage
        class Address {
            String street;
            String city;
            String state;
        }

        class Person {
            String name;
            int age;
            Address address;
            void speak() {}
        }

        Person person = new Person();
        Set<String> associatedClassNames = getAssociatedClassNames(person);

        for (String className : associatedClassNames) {
            System.out.println(className);
        }
    }
}
