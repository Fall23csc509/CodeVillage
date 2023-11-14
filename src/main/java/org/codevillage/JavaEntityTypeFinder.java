import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.utils.SourceRoot;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class JavaEntityTypeFinder {

    public static Optional<String> getEntityType(String filePath) {
        try {
            Path path = Path.of(filePath);
            SourceRoot sourceRoot = new SourceRoot(path);
            CompilationUnit cu = sourceRoot.parse(path);
            List<TypeDeclaration<?>> types = cu.getTypes();

            if (!types.isEmpty()) {
                TypeDeclaration<?> typeDeclaration = types.get(0); // assuming there's only one type in the file
                SimpleName typeName = typeDeclaration.getName();
                return Optional.of(typeDeclaration.getClass().getSimpleName() + ": " + typeName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public static void main(String[] args) {
        String filePath = "TestJavaFile.java"; // file path
        Optional<String> entityType = getEntityType(filePath);

        if (entityType.isPresent()) {
            System.out.println("Entity Type: " + entityType.get());
        } else {
            System.out.println("Failed to determine entity type.");
        }
    }
}
