package org.codevillage.fetching;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class LocalDataFetcher implements DataFetcher {
    @Override
    public void downloadPackage(String url, String targetPath) {
        try {
            // Remove the "file://" prefix if it exists
            if (url.startsWith("file://")) {
                url = url.substring(7);
            }

            // Create the target directory if it doesn't exist
            Path targetDirectory = new File(targetPath).toPath();
            if (!Files.exists(targetDirectory)) {
                Files.createDirectories(targetDirectory);
            }

            // Define the source and target directories as Paths
            Path sourceDirectory = new File(url).toPath();
            targetDirectory = new File(targetPath).toPath();

            // Copy the source directory and its contents to the target directory
            Path finalTargetDirectory = targetDirectory;
            Files.walkFileTree(sourceDirectory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path targetDir = finalTargetDirectory.resolve(sourceDirectory.relativize(dir));
                    Files.createDirectories(targetDir);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.copy(file, finalTargetDirectory.resolve(sourceDirectory.relativize(file)), StandardCopyOption.REPLACE_EXISTING);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            // Handle any exceptions that may occur during the copy process
            e.printStackTrace();
        }
    }
}
