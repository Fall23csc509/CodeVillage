package org.codevillage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class CameraKeyListener extends KeyAdapter {
    
    private Camera camera;

    @Override
    public void keyPressed(KeyEvent e) {
        int dx = 0;
        int dy = 0;

        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {
            dy = -10;
        } else if (keyCode == KeyEvent.VK_S) {
            dy = 10;
        } else if (keyCode == KeyEvent.VK_A) {
            dx = -10;
        } else if (keyCode == KeyEvent.VK_D) {
            dx = 10;
        }

        camera.moveCamera(dx, dy);
    }
}
