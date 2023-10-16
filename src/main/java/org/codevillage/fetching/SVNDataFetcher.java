package org.codevillage.fetching;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.wc2.SvnCheckout;
import org.tmatesoft.svn.core.wc2.SvnOperationFactory;
import org.tmatesoft.svn.core.wc2.SvnTarget;

import java.io.File;
import java.net.URL;


public class SVNDataFetcher implements DataFetcher {
    private final SvnOperationFactory svnOperationFactory;
    public SVNDataFetcher() {
        svnOperationFactory = new SvnOperationFactory();
    }

    @Override
    public void downloadPackage(String sourceUrl, String targetPath) {
        try {
            SVNURL svnurl = SVNURL.parseURIEncoded(sourceUrl);
            final SvnCheckout checkout = svnOperationFactory.createCheckout();
            checkout.setSingleTarget(SvnTarget.fromFile(new File(targetPath)));
            checkout.setSource(SvnTarget.fromURL(svnurl));
            checkout.run();
        } catch (SVNException e) {
            throw new RuntimeException(e);
        } finally {
            svnOperationFactory.dispose();
        }
    }
}
