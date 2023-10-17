package org.codevillage;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SourceCodeParser implements Parser
{
    private final Collection<File> files;

    public SourceCodeParser(Collection<File> files) {
        this.files = files;
    }

    /**
     * This method parses a list of Java source code files into a list of JavaClass's
     * @return The list of JavaClass's from the files
     * @throws IOException If there is a problem reading any of the given files
     */
    public List<JavaClass> parseSourceFiles() throws IOException
    {
        List<JavaClass> allClasses = new ArrayList<>();
        for(File file : this.files)
        {
            String fileContent = Files.readString(Paths.get(file.toURI()));
            CompilationUnit compilationUnit = StaticJavaParser.parse(fileContent);
            List<ParsedJavaClass> classes = new ArrayList<>();
            String packageName = compilationUnit.getPackageDeclaration().isPresent()
                    ? compilationUnit.getPackageDeclaration().get().getNameAsString() : "";
            VoidVisitor<List<ParsedJavaClass>> classCreatorVisitor = new ClassCreatorCollector(packageName);
            classCreatorVisitor.visit(compilationUnit, classes);

            allClasses.addAll(classes);
        }
        return allClasses;
    }

    private static class ClassCreatorCollector extends VoidVisitorAdapter<List<ParsedJavaClass>>
    {
        private final String packageName;

        public ClassCreatorCollector(String packageName) {
            super();
            this.packageName = packageName;
        }

        @Override
        public void visit(ClassOrInterfaceDeclaration n, List<ParsedJavaClass> collector)
        {
            super.visit(n, collector);
            if (!n.isInterface() && !n.isInnerClass()) {
                int nom = n.getMethods().size();
                int noa = n.getFields().size();
                String name = n.getNameAsString();
                collector.add(new ParsedJavaClass(name, this.packageName, nom, noa, new ArrayList<>()));
            }
        }
    }
}

class SourceCodeParserTest {

    @Test
    void parseSourceFiles_shouldParseAFile() throws IOException {
        File testDir = new File("test/test-package");

        File classAFile = new File(testDir, "org/test/ClassA.java");

        List<File> files = new ArrayList<>();
        files.add(classAFile);

        Parser p = new SourceCodeParser(files);
        List<JavaClass> parsedClasses = p.parseSourceFiles();

        // Ensure correct number of classes are parsed
        assertEquals(1, parsedClasses.size());

        // Validate class details
        for (JavaClass javaClass : parsedClasses) {
            assertEquals(javaClass.getNumberOfAttributes(), 2);
            assertEquals(javaClass.getNumberOfMethods(), 2);
            assertEquals("org.test", javaClass.getPackageName());
            assertEquals(javaClass.getClassName(), "ClassA");
        }
    }

    @Test
    void parseSourceFile_shouldIgnoreSubclasses() throws IOException {
        File testDir = new File("test/test-package");

        File multipleClassesFile = new File(testDir, "org/test/MultipleClasses.java");

        List<File> files = new ArrayList<>();
        files.add(multipleClassesFile);

        Parser p = new SourceCodeParser(files);
        List<JavaClass> parsedClasses = p.parseSourceFiles();

        // Ensure correct number of classes are parsed
        assertEquals(1, parsedClasses.size());

        // Validate class details
        for (JavaClass javaClass : parsedClasses) {
            assertEquals(1, javaClass.getNumberOfAttributes());
            assertEquals(javaClass.getNumberOfMethods(), 1);
            assertEquals("org.test", javaClass.getPackageName());
            assertEquals(javaClass.getClassName(), "MultipleClasses");
        }
    }

    @Test
    void parseSourceFile_shouldIgnoreInterface() throws IOException {
        File testDir = new File("test/test-package");

        File interfaceFile = new File(testDir, "org/test/MyInterface.java");

        List<File> files = new ArrayList<>();
        files.add(interfaceFile);

        Parser p = new SourceCodeParser(files);
        List<JavaClass> parsedClasses = p.parseSourceFiles();

        // Ensure correct number of classes are parsed
        assertEquals(0, parsedClasses.size());
    }
    @Test
    void parseSourceFile_canParseMultiple() throws IOException {
        File testDir = new File("test/test-package");

        List<File> files = new ArrayList<>();
        files.add(new File(testDir, "org/test/ClassA.java"));
        files.add(new File(testDir, "org/test/ClassB.java"));
        files.add(new File(testDir, "org/test/MyInterface.java"));
        files.add(new File(testDir, "org/test/MultipleClasses.java"));
        files.add(new File(testDir, "org/test/nested/NestedClass.java"));

        Parser p = new SourceCodeParser(files);
        List<JavaClass> parsedClasses = p.parseSourceFiles();

        // Ensure correct number of classes are parsed
        assertEquals(4, parsedClasses.size());

        assertEquals(parsedClasses.get(0), new ParsedJavaClass("ClassA", "org.test",
                2, 2, new ArrayList<>()));
        assertEquals(parsedClasses.get(1), new ParsedJavaClass("ClassB", "org.test",
                3, 3, new ArrayList<>()));
        assertEquals(parsedClasses.get(2), new ParsedJavaClass("MultipleClasses", "org.test",
                1, 1, new ArrayList<>()));
        assertEquals(parsedClasses.get(3), new ParsedJavaClass("NestedClass", "org.test.nested",
                0, 0, new ArrayList<>()));
    }
}
