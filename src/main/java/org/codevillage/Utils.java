package org.codevillage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utils {
    public static void deleteDirectory(Path directory) throws IOException {
        Files.walk(directory)
                .sorted((a, b) -> -a.compareTo(b))
                .forEach(path -> {
                    try {
                        Files.delete(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
