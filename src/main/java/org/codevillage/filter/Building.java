package org.codevillage.filter;

import java.util.ArrayList;

public interface Building {
    int getMethods();
    int getAttributes();
    String getDistrict();
    String getName();
    ArrayList<String> getRelations();

    String getCategory();
}
