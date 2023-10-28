package org.codevillage;

public class Camera {
    private int cameraX;
    private int cameraY;

    public Camera() {
        cameraX = 0;
        cameraY = 0;
    }

    public int getCameraX() {
        return cameraX;
    }

    public int getCameraY() {
        return cameraY;
    }

    public void setCameraX(int cameraX) {
        this.cameraX = cameraX;
    }

    public void setCameraY(int cameraY) {
        this.cameraY = cameraY;
    }

    public void moveCamera(int dx, int dy) {
        setCameraX(cameraX + dx);
        setCameraY(cameraY + dy);
    }
}
