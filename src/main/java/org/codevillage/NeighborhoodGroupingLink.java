package org.codevillage;

import java.util.*;

public class NeighborhoodGroupingLink extends ShapeChainLink{
    public NeighborhoodGroupingLink(ShapeChain next) {
        super(next);
    }
    @Override
    public void position(ArrayList<JavaEntity> entities) {
        ArrayList<JavaClass> filtered = filterEntities(entities);
        NeighborhoodWrapper nw = groupClasses(filtered);
        // for each name in the global singleton hashset, pop the javaentity out of nw and save it to a list.
        HashSet<String> composerNames = ShapePositioningData.getInstance().getComposerNames();
        ArrayList<String> composerList = new ArrayList<>(composerNames.stream().toList());
        // getComposers removes all the composers from nw and returns them
        ArrayList<JavaEntity> composers = getComposers(nw, composerList);
        // then for each javaentity in the list, determine the highest level occurence of it in a neighborhood's
        // composition list. insert the javaentity into that neighborhood.
        replaceComposers(nw, composers);
        // For each sub neighborhood, we check if after replacement one of its composers is still within either it
        // or one of its sub neighborhoods, if not we dissolve the neighborhood and add its entities to its
        // parent
        dissolveExtras(nw);
        ShapePositioningData.getInstance().setNeighborhoodWrapper(nw);
    }
    void dissolveExtras(NeighborhoodWrapper neighborhoodWrapper) {
        ArrayList<NeighborhoodWrapper> neighborhoodWrappers = neighborhoodWrapper.getNeighborhoods();
        Iterator<NeighborhoodWrapper> neighborhoodWrapperIterator = neighborhoodWrappers.stream().iterator();
        while (neighborhoodWrapperIterator.hasNext()) {
            NeighborhoodWrapper nw = neighborhoodWrapperIterator.next();
            Boolean dissolve = true;
            for (String composition : nw.getCompositions()) {
                dissolveExtras(nw);
                for (JavaEntity entity : nw.getEntities()) {
                    if (entity.getName().equals(composition)) {
                        dissolve = false;
                    }
                }
            }
            if (dissolve) {
                neighborhoodWrapper.getEntities().addAll(nw.getEntities());
                neighborhoodWrapper.getNeighborhoods().addAll(nw.getNeighborhoods());
                neighborhoodWrapper.getNeighborhoods().remove(nw);
            }
        }
    }
    void replaceComposers(NeighborhoodWrapper neighborhoodWrapper, ArrayList<JavaEntity> composers) {
        for (JavaEntity composer : composers) {
            ArrayList<NeighborhoodWrapper> neighborhoodWrappers = new ArrayList<>(neighborhoodWrapper.getNeighborhoods());
            while (!neighborhoodWrappers.isEmpty()) {
                boolean found = false;
                NeighborhoodWrapper nw = neighborhoodWrappers.remove(0);
                for (String composerName : nw.getCompositions()) {
                    if (composer.getName().equals(composerName)) {
                        nw.addEntity(composer);
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
                neighborhoodWrappers.addAll(nw.getNeighborhoods());
            }
        }
    }
    private ArrayList<JavaEntity> getComposers(NeighborhoodWrapper neighborhoodWrapper, ArrayList<String> composerList) {
        ArrayList<JavaEntity> composers = new ArrayList<>();
        for (String composerName : composerList) {
            composers.add(getComposer(neighborhoodWrapper, composerName));

        }
        return composers;
    }
    private JavaEntity getComposer(NeighborhoodWrapper neighborhoodWrapper, String composerName) {
        ArrayList<JavaEntity> entities = neighborhoodWrapper.getEntities();
        Iterator<JavaEntity> entityIterator = entities.iterator();
        while (entityIterator.hasNext()) {
            JavaEntity entity = entityIterator.next();
            if (entity.getName().equals(composerName)) {
                neighborhoodWrapper.getEntities().remove(entity);
                return entity;
            }
        }
        for (NeighborhoodWrapper nh : neighborhoodWrapper.getNeighborhoods()) {
            JavaEntity composer = getComposer(nh, composerName);
            if (composer != null) {
                return composer;
            }
        }
        return null;
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
        NeighborhoodWrapper neighborhoodWrapper = new NeighborhoodWrapper();
        // make this stream array list
        ArrayList<JavaClass> topLevelMembers = new ArrayList<>(classes.stream().filter(c -> c.getCompositions().isEmpty()).toList());
        // add everything from topLevelMembers to neighborhoodWrapper
        // then filter out the others
        ArrayList<JavaClass> subNeighborhoodMembers = new ArrayList<>(classes.stream().filter(c -> !c.getCompositions().isEmpty()).toList());
        for (JavaClass topLevelMember : topLevelMembers) {
            neighborhoodWrapper.addEntity(topLevelMember);
        }
        // Loop over all the sub neighborhood members until they are all in neighborhoods.
        while (!subNeighborhoodMembers.isEmpty()) {
            HashMap<String, Integer> compositions = new HashMap<>();
            ArrayList<JavaClass> currentMembers = new ArrayList<>();
            String mostCommon = "";
            int highVal = 0;
            // Find the most common composition in the current group of sub
            // neighborhood members.
            for (JavaClass javaClass : classes) {
                for (String s : javaClass.getCompositions()) {
                    if (!compositions.containsKey(s)) {
                        compositions.put(s, 1);
                    }
                    else {
                        compositions.replace(s, compositions.get(s) + 1);
                    }
                    if (compositions.get(s) > highVal) {
                        mostCommon = s;
                        highVal = compositions.get(s);
                    }
                }
            }
            // Put all of the Java Classes that have the most common composition into the
            // neighborhood.
            Iterator<JavaClass> javaClassIterator = subNeighborhoodMembers.iterator();
            while (javaClassIterator.hasNext()){
                JavaClass javaClass = javaClassIterator.next();
                if (javaClass.getName().equals(mostCommon)) {
                    currentMembers.add(javaClass);
                    javaClassIterator.remove();
                    break;
                }
                for (String s : javaClass.getCompositions()) {
                    if (s.equals(mostCommon)) {
                        currentMembers.add(javaClass);
                        javaClassIterator.remove();
                        break;
                    }
                }
            }
            // Get all the most common compositions by first turning the list of compositions
            // for each current member of the neighborhood into a set. Then run set intersections
            // on each set until only the compositions that appear in all of them are left.
            HashSet<String> commonCompositions = currentMembers.stream()
                    .map(c -> new HashSet<>(c.getCompositions()))
                    .reduce(new HashSet<>(currentMembers.get(0).getCompositions()),
                            (c, p) -> {c.retainAll(p); return c;});
            // Go through all current members and removes all the most common compositions
            currentMembers.forEach(c -> c.getCompositions().removeAll(commonCompositions));
            // Recursively group all members of the current neighborhood into sub-neighborhoods
            NeighborhoodWrapper nw = groupClasses(currentMembers);
            commonCompositions.forEach(c -> nw.addComposition(c));
            // save all common compositions to global singleton HashSet
            commonCompositions.forEach(c -> ShapePositioningData.getInstance().getComposerNames().add(c));
            neighborhoodWrapper.addNeighborhood(nw);
        }
        return neighborhoodWrapper;
    }
}
