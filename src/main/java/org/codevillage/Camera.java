package org.codevillage;

import java.awt.event.KeyListener;
import java.beans.PropertyChangeSupport;

public class Camera {
    private int cameraX;
    private int cameraY;
    private KeyListener keyListener;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private static Camera instance = new Camera();

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

    public int getCameraY() {
        return cameraY;
    }

    public void setCameraX(int cameraX) {
        int oldX = this.cameraX;
        this.cameraX = cameraX;
        propertyChangeSupport.firePropertyChange("cameraX", oldX, cameraX);
    }

    public void setCameraY(int cameraY) {
        int oldY = this.cameraY;
        this.cameraY = cameraY;
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
