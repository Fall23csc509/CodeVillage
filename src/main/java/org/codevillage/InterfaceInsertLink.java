package org.codevillage;

import java.util.ArrayList;

public class InterfaceInsertLink extends ShapeChainLink {
    public InterfaceInsertLink(ShapeChainLink next) {
        super(next);
    }
    @Override
    public void position(ArrayList<JavaEntity> entities) {
        for (JavaEntity entity : entities) {
            if (entity.getType() == JavaEntityType.JAVA_INTERFACE) {
                int mostRealizations = 0;
                NeighborhoodWrapper nh = ShapePositioningData.getInstance().getNeighborhoodWrapper();
                NeighborhoodWrapper mostRealizationsWrapper = nh;
                NeighborhoodWrapper nhCurr = null;
                ArrayList<NeighborhoodWrapper> nhQueue = new ArrayList<>();
                nhQueue.add(nh);
                while (!nhQueue.isEmpty()) {
                    nhCurr = nhQueue.remove(0);
                    int counter = 0;
                    for (JavaEntity otherEntity : entities) {
                        if (otherEntity.getType() == JavaEntityType.JAVA_BASE_CLASS ||
                                otherEntity.getType() == JavaEntityType.JAVA_ABSTRACT_CLASS) {
                            JavaClass otherJavaClass = (JavaClass) otherEntity;
                            if (otherJavaClass.getRealizations().contains(entity.getFullyQualifiedName())) {
                                counter++;
                                if (counter > mostRealizations) {
                                    mostRealizations = counter;
                                    mostRealizationsWrapper = nhCurr;
                                }
                            }
                        }
                    }
                    nhQueue.addAll(nhCurr.getNeighborhoods());
                }
                mostRealizationsWrapper.addEntity(entity);
            }
        }
        this.next.position(entities);
    }
}
