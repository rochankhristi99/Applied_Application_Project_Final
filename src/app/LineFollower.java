package app;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;


public class LineFollower extends Thread {
	  private DataExchange DEObj;
    private static final int MAX_SPEED = 500;
    private static final int BASE_SPEED = 100;
    private static final int SENSOR_THRESHOLD = 25;

    private EV3ColorSensor lightSensor; 
    private SampleProvider sampleProvider;
    private float[] sample;

    RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.D);
    RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
    
    
    public LineFollower(DataExchange DE) {
        DEObj = DE;
        lightSensor = new EV3ColorSensor(SensorPort.S3);
        sampleProvider = lightSensor.getRedMode();
        sample = new float[sampleProvider.sampleSize()];

    }

    public void run() {
        while (true) {
        	
            sampleProvider.fetchSample(sample, 0);
            int sensorValue = (int) (sample[0] * 100); // Convert sensor reading to percentage
            //System.out.println("Color Value: " + sensorValue);
            
           /* if(DEObj.getCMD()==0)
            {
                Sound.twoBeeps();
                Sound.twoBeeps();
           	    leftMotor.setSpeed(100);
				rightMotor.setSpeed(200);
				leftMotor.forward();
				rightMotor.forward();
				Delay.msDelay(2000);
				DEObj.setCMD(1);
				
				OR
				
				  Sound.twoBeeps();
                Sound.twoBeeps();
                
                //Turn left side
            	leftMotor.setSpeed(0);
				rightMotor.setSpeed(200);
				leftMotor.forward();
				rightMotor.forward();
				Delay.msDelay(1000);
            }
            */
            if(DEObj.getCMD()==0)
            {
                Sound.twoBeeps();
                Sound.twoBeeps();
                
                //Turn left side
            	leftMotor.setSpeed(10);
				rightMotor.setSpeed(200);
				leftMotor.backward();
				rightMotor.forward();
				Delay.msDelay(1000);
				
							
				//DEObj.setCMD(1);

            }
            else  if(DEObj.getCMD()==1)
            {
            	  int difference = sensorValue - SENSOR_THRESHOLD;
                  int leftSpeed = BASE_SPEED + difference * 3;
                  int rightSpeed = BASE_SPEED - difference*5;
                  
                 leftSpeed = Math.max(0, Math.min(MAX_SPEED, leftSpeed));
             
                  	 leftMotor.setSpeed(leftSpeed);
                       rightMotor.setSpeed(rightSpeed);      
                       leftMotor.forward();
                       rightMotor.forward();	
            }          
        }
    }
}
