package org.codevillage;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SourceFileChecker implements FileChecker {
    @Override
    public List<Path> getFilesToParse(Path directory) {
        List<Path> javaFiles = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory)) {
            for (Path path : stream) {
                if (Files.isRegularFile(path) && path.toString().endsWith(".java")) {
                    javaFiles.add(path);
                } else if (Files.isDirectory(path)) {
                    javaFiles.addAll(getFilesToParse(path));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return javaFiles;
    }
}