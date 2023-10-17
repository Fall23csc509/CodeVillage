package org.codevillage;
import java.util.List;

public class VillageController {
    private Village village;
    private Selector selector;
    private Tagger tagger;

    public VillageController() {
        this.village = new Village();
        this.selector = new Selector();
        this.tagger = new Tagger();
    }

    // Requirement 1: Create a populated Village from parsed data
    public void createVillageFromParsedData(List<Building> buildings) {
        village.addBuildings(buildings);
    }

    // Requirement 2: Create a populated Village from a selection (spawning)
    public void createVillageFromSelection(List<Building> selectedBuildings) {
        village.setBuildings(selectedBuildings);
    }

    // Requirement 3: Select a singular building
    public void selectBuilding(Building building) {
        selector.addBuilding(building);
    }

    // Requirement 4: Invert the selection of buildings
    public void invertSelection() {
        selector.invertSelection(village.getAllBuildings());
    }

    // Requirement 5: Change the transparency level of tagged buildings
    public void changeTransparencyOfTaggedBuildings(String tag, int transparencyLevel) {
        List<Building> taggedBuildings = tagger.getBuildingsWithTag(tag);
        for (Building building : taggedBuildings) {
            building.setTransparency(transparencyLevel);
        }
    }

    // Requirement 6: Select buildings in the Village that match a search string
    public List<Building> selectBuildingsBySearchString(String searchString) {
        return village.getBuildingsMatchingSearchString(searchString);
    }

    // Requirement 7: Select buildings in the Village that match a package (the district)
    public List<Building> selectBuildingsByPackage(String packageName) {
        return village.getBuildingsInPackage(packageName);
    }

    // Requirement 8: Select buildings in the Village that match a category (root classes)
    public List<Building> selectBuildingsByCategory(String category) {
        return village.getBuildingsByCategory(category);
    }

    // Requirement 9: Select buildings in the Village that relate to the current selection
    public List<Building> selectBuildingsRelatedToSelection() {
        return village.getBuildingsRelatedToSelection(selector.getSelectedBuildings());
    }

    // Requirement 10: Apply a color transformation to all selected buildings
    public void applyColorTransformationToSelectedBuildings(ColorTransformation transformation) {
        List<Building> selectedBuildings = selector.getSelectedBuildings();
        for (Building building : selectedBuildings) {
            building.applyColorTransformation(transformation);
        }
    }
}
