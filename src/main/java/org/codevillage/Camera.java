package org.codevillage;

import java.awt.event.KeyListener;
import java.beans.PropertyChangeSupport;

public class Camera {
    private static final Camera instance = new Camera();
    private int cameraX;
    private int cameraY;
    private final KeyListener keyListener;
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    private Camera() {
        cameraX = 0;
        cameraY = 0;
        keyListener = new CameraKeyListener();
    }

    public static Camera getInstance() {
        return instance;
    }

    public int getCameraX() {
        return cameraX;
    }

    public void setCameraX(int cameraX) {
        int oldX = this.cameraX;
        this.cameraX = cameraX;
        CanvasDataUpdater.update();
        propertyChangeSupport.firePropertyChange("cameraX", oldX, cameraX);
    }

    public int getCameraY() {
        return cameraY;
    }

    public void setCameraY(int cameraY) {
        int oldY = this.cameraY;
        this.cameraY = cameraY;
        CanvasDataUpdater.update();
        propertyChangeSupport.firePropertyChange("cameraY", oldY, cameraY);
    }

    public void moveCamera(int dx, int dy) {
        setCameraX(cameraX + dx);
        setCameraY(cameraY + dy);
    }

    public KeyListener getKeyListener() {
        return keyListener;
    }
}
