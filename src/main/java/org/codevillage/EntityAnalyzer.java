import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityAnalyzer {

    public static List<String> getReferencedClassNames(Object entity) {
        List<String> classNames = new ArrayList<>();
        Class<?> entityClass = entity.getClass();

        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            Class<?> fieldType = field.getType();
            if (!fieldType.isPrimitive()) {
                classNames.add(fieldType.getName());
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
        }

        Person person = new Person();
        List<String> referencedClassNames = getReferencedClassNames(person);

        for (String className : referencedClassNames) {
            System.out.println(className);
        }
    }
}
