package app;

import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;

public class ObstacleDetector extends Thread {
    private DataExchange DEObj;
    private EV3UltrasonicSensor ultrasonicSensor;
    private final float securityDistance = 0.20f;
  

    public ObstacleDetector(DataExchange DE) {
        DEObj = DE;
        ultrasonicSensor = new EV3UltrasonicSensor(SensorPort.S2);
    }
   
    public void run() {
        while (true) {
            float[] sample = new float[ultrasonicSensor.sampleSize()];
            ultrasonicSensor.getDistanceMode().fetchSample(sample, 0);
            float distance = sample[0];

            if (distance > securityDistance) {
                DEObj.setCMD(1);
            } else {      
                DEObj.setCMD(0);
                LCD.drawString("Obstacle found!", 0, 1);
            }
        }
    }
}
