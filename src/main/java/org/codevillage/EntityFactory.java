package org.codevillage;

import java.io.File;

public interface EntityFactory {
    JavaEntity createEntityFromFile(File f);
}
