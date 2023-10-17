package org.codevillage;
import java.awt.Color;

public class ColorTransformation {
    private int redChange;
    private int greenChange;
    private int blueChange;

    public ColorTransformation(int redChange, int greenChange, int blueChange) {
        this.redChange = redChange;
        this.greenChange = greenChange;
        this.blueChange = blueChange;
    }

    public Color applyTransformation(Color originalColor) {
        int originalRed = originalColor.getRed();
        int originalGreen = originalColor.getGreen();
        int originalBlue = originalColor.getBlue();

        int transformedRed = clamp(originalRed + redChange, 0, 255);
        int transformedGreen = clamp(originalGreen + greenChange, 0, 255);
        int transformedBlue = clamp(originalBlue + blueChange, 0, 255);

        return new Color(transformedRed, transformedGreen, transformedBlue);
    }

    private int clamp(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }
}
