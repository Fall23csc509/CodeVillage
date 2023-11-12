package org.codevillage;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;

public class NeighborhoodGroupingLink extends ShapeChainLink{
    @Override
    public NeighborhoodWrapper position(ArrayList<JavaEntity> entities) {
        ArrayList<JavaClass> filtered = filterEntities(entities);
        return groupClasses(filtered);
    }
    private ArrayList<JavaClass> filterEntities(ArrayList<JavaEntity> entities) {
        ArrayList<JavaClass> filtered = new ArrayList<>();
        for (JavaEntity entity : entities) {
            if (entity.getType() == JavaEntityType.JAVA_ABSTRACT_CLASS ||
            entity.getType() == JavaEntityType.JAVA_BASE_CLASS) {
                filtered.add((JavaClass) entity);
            }
        }
        return filtered;
    }
    private NeighborhoodWrapper groupClasses(ArrayList<JavaClass> classes) {

    }
}
