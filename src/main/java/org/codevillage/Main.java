package org.codevillage;

public class Main {
    public static void main(String[] args) {
        final SVNDataFetcher fetcher = new SVNDataFetcher();
        // make dir temp
        fetcher.downloadPackage("", "./.temp");
    }
}