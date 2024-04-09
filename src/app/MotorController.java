package app;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

/**
 * The MotorController class controls the movement of motors.
 * It extends Thread to allow asynchronous operation.
 */
public class MotorController extends Thread {
    // The left and right motors for controlling movement
    private RegulatedMotor leftMotor;
    private RegulatedMotor rightMotor;

    /**
     * Constructs a new MotorController object and initializes the motors.
     */
    public MotorController() {
        // Initialize the left motor connected to port D
        leftMotor = new EV3LargeRegulatedMotor(MotorPort.D);
    
        // Initialize the right motor connected to port C
        rightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
    }

    /**
     * Sets the speed of both motors.
     * @param leftSpeed The speed of the left motor.
     * @param rightSpeed The speed of the right motor.
     */
    public void setSpeed(int leftSpeed, int rightSpeed) {
        // Set the speed of the left and right motors
        leftMotor.setSpeed(leftSpeed);
        rightMotor.setSpeed(rightSpeed);
    }

    /**
     * Moves both motors forward.
     */
    public void forward() {
        // Move both motors forward
        leftMotor.forward();
        rightMotor.forward();
    }

    /**
     * Stops both motors.
     */
    public void stopMotor() {
        // Stop both motors
        leftMotor.stop();
        rightMotor.stop();
    }
}
