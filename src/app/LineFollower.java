package app;

import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import lejos.utility.Stopwatch;

/**
 * The LineFollower class controls the robot's movement based on line following logic.
 * It extends Thread to allow asynchronous operation.
 */
public class LineFollower extends Thread {
    // The DataExchange object for exchanging data with other components
    private DataExchange DEObj;
    
    // The ColorSensor object for detecting the line
    private ColorSensor colorSensor;
    
    // The MotorController object for controlling motor movement
    private MotorController motorController;
    
    // Constants for motor speeds and sensor threshold
    private static final int MAX_SPEED = 500;
    private static final int BASE_SPEED = 100;
    private static final int SENSOR_THRESHOLD = 25;
    
    // Stopwatch for measuring elapsed time
    private Stopwatch stopwatch;
    
    // Counter for detecting line crossings
    private int detectionCount = 0;
    
    // BackgroundMusicPlayer for playing music
    BackgroundMusicPlayer musicPlayer;

    /**
     * Constructs a new LineFollower object.
     * @param DE The DataExchange object for exchanging data.
     */
    public LineFollower(DataExchange DE) {
        // Initialize DataExchange object
        DEObj = DE;
    
        // Initialize ColorSensor object
        colorSensor = new ColorSensor();
        
        // Initialize MotorController object
        motorController = new MotorController();
        
        // Initialize Stopwatch
        stopwatch = new Stopwatch();
        
        // Initialize BackgroundMusicPlayer
        musicPlayer = new BackgroundMusicPlayer();
    }

    /**
     * The main task of the line follower.
     */
    public void run() {
        // Start playing background music
        musicPlayer.start();
        
        // Continuous operation
        while (true) {
        	// Get sensor reading
            int sensorValue = colorSensor.getSensorValue();

            // Check current command
            if (DEObj.getCMD() == 0) {
            
            	// Increment detection count
                detectionCount++;
                LCD.drawString("Count: " + detectionCount, 0, 2);

                // Check if robot has crossed line twice
                if (detectionCount >= 2) {
                    stopRobot(); // Stop robot
                    break; // Exit loop
                }

                // Sound indication
                Sound.twoBeeps();

                // Turn left
                motorController.setSpeed(150, 300);
                motorController.forward();
                Delay.msDelay(700);

                // Move forward
                motorController.setSpeed(200, 200);
                motorController.forward();
                Delay.msDelay(500);

                // Turn right
                motorController.setSpeed(300, 0);
                motorController.forward();
                Delay.msDelay(900);

                // Move forward
                motorController.setSpeed(300, 300);
                motorController.forward();
                Delay.msDelay(1000);

                // Move slightly right to align with line
                motorController.setSpeed(300, 200);
                motorController.forward();
                Delay.msDelay(2000);

                // Set command to continue following the line
                DEObj.setCMD(1);

            } else if (DEObj.getCMD() == 1) {
                // Calculate speed based on sensor value
                int difference = sensorValue - SENSOR_THRESHOLD;
                int leftSpeed = BASE_SPEED + difference * 3;
                int rightSpeed = BASE_SPEED - difference * 5;
                leftSpeed = Math.max(0, Math.min(MAX_SPEED, leftSpeed));

                // Set motor speeds and move forward
                motorController.setSpeed(leftSpeed, rightSpeed);
                motorController.forward();
            }
        }
    }

    /**
     * Stops the robot and displays total elapsed time.
     */
    public void stopRobot() {
        // Stop motors
        motorController.stopMotor();
        
        // Get elapsed time
        long elapsedTime = stopwatch.elapsed();
        
        // Display total time
        LCD.drawString("Total Time: " + elapsedTime / 1000 + " sec", 0, 3);
        LCD.drawString("Finish...", 0, 4);
    }
}
