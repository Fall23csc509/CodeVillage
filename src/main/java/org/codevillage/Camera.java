package org.codevillage;

public class Camera implements MovementController {
    private Vector3 position;
    // NOTE - Lucas: I think we should also use Vector3 for rotations, more self-explanatory.
    // Thus: private Vector3 rotation;
    private float yaw;
    private float pitch;
    private MovementController movementController;

    // Constructor
    public Camera() {
        this.position = new Vector3(0, 0, 0); // Initialize the position

        this.yaw = 0.0f; // Initialize yaw
        this.pitch = 0.0f; // Initialize pitch

        // this.movementController = new HorizontalMovementController(); // Initialize with HorizontalMovementController
    }

    // Implement methods from the MovementController interface
    @Override
    public void moveForward() {
        movementController.moveForward();
    }

    @Override
    public void moveBackward() {
        movementController.moveBackward();
    }

    @Override
    public void moveLeft() {
        movementController.moveLeft();
    }

    @Override
    public void moveRight() {
        movementController.moveRight();
    }

    // Other methods and properties specific to the Camera class

    // Getter and setter methods for position, yaw, and pitch

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    // Getter and setter for the movement controller

    public MovementController getMovementController() {
        return movementController;
    }

    public void setMovementController(MovementController movementController) {
        this.movementController = movementController;
    }
}
