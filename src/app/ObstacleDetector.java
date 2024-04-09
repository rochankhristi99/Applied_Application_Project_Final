package app;

import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;

/**
 * The ObstacleDetector class detects obstacles using an ultrasonic sensor.
 * It extends Thread to allow asynchronous operation.
 */
public class ObstacleDetector extends Thread {
    // The DataExchange object for exchanging data with other components
    private DataExchange DEObj;
   
    // The ultrasonic sensor for detecting obstacles
    private EV3UltrasonicSensor ultrasonicSensor;
    
    // The distance threshold for detecting obstacles
    private final float securityDistance = 0.20f;

    /**
     * Constructs a new ObstacleDetector object and initializes the ultrasonic sensor.
     * @param DE The DataExchange object for exchanging data.
     */
    public ObstacleDetector(DataExchange DE) {
        // Initialize the DataExchange object
        DEObj = DE;
        // Initialize the ultrasonic sensor connected to port S2
        ultrasonicSensor = new EV3UltrasonicSensor(SensorPort.S2);
    }

    /**
     * The main task of the obstacle detector.
     */
    public void run() {
        // Continuously monitor for obstacles
        while (true) {
            // Array to store sensor readings
            float[] sample = new float[ultrasonicSensor.sampleSize()];
           
            // Fetch the distance reading from the ultrasonic sensor
            ultrasonicSensor.getDistanceMode().fetchSample(sample, 0);
            
            // Extract the distance value from the sample
            float distance = sample[0];

            // Check if the distance exceeds the security threshold
            if (distance > securityDistance) {
                // If no obstacle detected, set the command to move forward
                DEObj.setCMD(1);
            } else {
                // If obstacle detected, set the command to stop and display a message
                DEObj.setCMD(0);
                LCD.drawString("Obstacle found!", 0, 1);
            }
        }
    }
}
