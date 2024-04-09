package app;

import lejos.hardware.Button;
import lejos.hardware.Sound;

/**
 * The main class to control the execution flow of the application.
 */
public class Main {
    private static DataExchange DE;
    private static LineFollower LFObj;
    private static ObstacleDetector ODObj;
    private static ColorSensor colorSensor;
    private static MotorController motorController;

    /**
     * The main method to start the application.
     * @param args The command line arguments (not used).
     */
    
    public static void main(String[] args) {

        System.out.println("Let's Begin...");
        Sound.beepSequenceUp();   
        Button.waitForAnyPress();
        Button.LEDPattern(4);  

        // Initialize data exchange, obstacle detector, line follower, color sensor, and motor controller
        DE = new DataExchange();
        ODObj = new ObstacleDetector(DE);
        LFObj = new LineFollower(DE);
        colorSensor = new ColorSensor();
        motorController = new MotorController();
     
        // Start obstacle detector, line follower, color sensor, and motor controller threads
        ODObj.start();
        LFObj.start();
        colorSensor.start();
        motorController.start();
        
        // Wait for the ENTER button to be pressed to exit the program
        while (Button.ENTER.isDown()) { 
           System.exit(0);
        }
        
        // Interrupt threads to stop them gracefully
        ODObj.interrupt();
        LFObj.interrupt();
        colorSensor.interrupt();
        motorController.interrupt();
    }
}
