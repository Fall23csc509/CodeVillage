package org.codevillage.fetching;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc2.SvnExport;
import org.tmatesoft.svn.core.wc2.SvnTarget;

import java.io.File;

public class SVNDataFetcher implements DataFetcher {
    private final SVNClientManager clientManager = SVNClientManager.newInstance();
    private final ISVNAuthenticationManager authManager = clientManager.getOperationFactory().getAuthenticationManager();

    @Override
    public void downloadPackage(String url, String targetPath) {
        File target = new File(targetPath);
        try {
            SVNURL svnurl = SVNURL.parseURIEncoded(url);
            final SvnExport checkout = clientManager.getOperationFactory().createExport();
            checkout.setSource(SvnTarget.fromURL(svnurl));
            checkout.setSingleTarget(SvnTarget.fromFile(target));
        } catch (SVNException e) {
            throw new RuntimeException(e);
        } finally {
            clientManager.getOperationFactory().dispose(); // release resources of the factory.
        }
    }
}
