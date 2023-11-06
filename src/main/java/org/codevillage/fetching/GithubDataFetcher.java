package org.codevillage.fetching;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;


public class GithubDataFetcher implements DataFetcher {
    @Override
    public void downloadPackage(String sourceUrl, String targetPath) {
        // Create a temporary folder
        final File tempFolder = new File(targetPath);
        try {
            // Create a Git clone command
            CloneCommand cloneCommand = Git.cloneRepository()
                    .setURI(sourceUrl)
                    .setDirectory(tempFolder);

            // Execute the clone command
            Git git = cloneCommand.call();

            // The repository has been successfully cloned into the target directory
            System.out.println("Repository cloned to: " + targetPath);

            // Clean up and close the Git object
            git.close();
        } catch (GitAPIException e) {
            throw new RuntimeException(e);
        }
    }
}
