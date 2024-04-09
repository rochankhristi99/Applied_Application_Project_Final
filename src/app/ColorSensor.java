package app;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

/**
 * The ColorSensor class represents a color sensor used to detect light intensity.
 * It extends Thread to allow asynchronous operation.
 */
public class ColorSensor extends Thread {
    // The EV3ColorSensor object for accessing the color sensor
    private EV3ColorSensor lightSensor;
    
    // The SampleProvider for fetching sensor readings
    private SampleProvider sampleProvider;
    
    // Array to store sensor readings
    private float[] sample;

    /**
     * Constructs a new ColorSensor object and initializes the sensor.
     */
    public ColorSensor() {
        // Initialize the color sensor connected to port S3
        lightSensor = new EV3ColorSensor(SensorPort.S3);
      
        // Set the sensor mode to measure light intensity (red mode)
        sampleProvider = lightSensor.getRedMode();
        
        // Initialize the sample array to store sensor readings
        sample = new float[sampleProvider.sampleSize()];
    }

    /**
     * Gets the current sensor reading.
     * @return The light intensity measured by the sensor (scaled to 0-100).
     */
    public int getSensorValue() {
        // Fetch the sensor reading into the sample array
        sampleProvider.fetchSample(sample, 0);
      
        // Scale the sensor reading to a range of 0-100 and return it
        return (int) (sample[0] * 100);
    }
}
