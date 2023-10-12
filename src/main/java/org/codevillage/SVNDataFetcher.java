package org.codevillage;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc2.SvnExport;
import org.tmatesoft.svn.core.wc2.SvnOperationFactory;
import org.tmatesoft.svn.core.wc2.SvnTarget;

import java.io.File;

public class SVNDataFetcher implements DataFetcher {
    private final SVNClientManager clientManager = SVNClientManager.newInstance();

    @Override
    public boolean downloadPackage(String url, String targetPath) {
        File target = new File(targetPath);
        final SvnOperationFactory svnOperationFactory = new SvnOperationFactory();
        try {
            SVNURL svnurl = SVNURL.parseURIEncoded(url);
            final SvnExport checkout = svnOperationFactory.createExport();
            checkout.setSource(SvnTarget.fromURL(svnurl));
            checkout.setSingleTarget(SvnTarget.fromFile(target));
        } catch (SVNException e) {
            throw new RuntimeException(e);
        } finally {
            svnOperationFactory.dispose(); // release resources of the factory.
        }
        return true;
    }
}
