package org.codevillage;
import java.util.ArrayList;
import java.util.List;

class Building {
    private String name;
    private int transparency;
    private String category;

    public Building(String name, int transparency, String category) {
        this.name = name;
        this.transparency = transparency;
        this.category = category;
    }

    // Getters and Setters for Building properties

    public void applyColorTransformation(ColorTransformation transformation) {
        // Implementation to apply a color transformation
    }
}
