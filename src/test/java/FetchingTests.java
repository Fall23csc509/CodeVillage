import org.codevillage.Utils;
import org.codevillage.fetching.GithubDataFetcher;
import org.codevillage.fetching.LocalDataFetcher;
import org.codevillage.fetching.SVNDataFetcher;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class FetchingTests {
    final String cwd = System.getProperty("user.dir");
    final String targetPath = String.format("%s/src/test/resources/.temp", cwd);
    @Test
    public void testGithubFetching() {
        final GithubDataFetcher githubDataFetcher = new GithubDataFetcher();
        // make dir temp
        githubDataFetcher.downloadPackage(
                "https://github.com/Fall23csc509/CodeVillage.git", targetPath);
        final File tempFolder = new File(targetPath);
        assert (tempFolder.listFiles().length > 0);
        // delete dir temp
        try {
            Utils.deleteDirectory(tempFolder.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert(!tempFolder.exists());
    }
    @Test
    public void testSVNFetching() {
        final SVNDataFetcher svnDataFetcher = new SVNDataFetcher();
        // make dir temp
        svnDataFetcher.downloadPackage(
                String.format("file://%s/src/test/resources/svn_test_repo", cwd), targetPath
        );
        final File tempFolder = new File(targetPath);
        assert (tempFolder.listFiles().length > 0);
        // delete dir temp
        try {
            Utils.deleteDirectory(tempFolder.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert(!tempFolder.exists());
    }
    @Test
    public void testLocalFetching() {
        final LocalDataFetcher localDataFetcher = new LocalDataFetcher();

        // Provide the source file URL and target directory
        String sourceFileURL = String.format("file://%s/src/test/resources/local_test_folder", cwd);
        String targetDirectory = targetPath;

        localDataFetcher.downloadPackage(sourceFileURL, targetDirectory);
        // Verify that the files were copied successfully
        final File tempFolder = new File(targetDirectory);
        assert (tempFolder.listFiles().length > 0);

        // Delete the temp directory
        try {
            Utils.deleteDirectory(tempFolder.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert (!tempFolder.exists());
    }
}
