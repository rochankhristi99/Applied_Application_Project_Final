package app;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import lejos.utility.Stopwatch;


public class LineFollower extends Thread {
	  private DataExchange DEObj;
    private static final int MAX_SPEED = 500;
    private static final int BASE_SPEED = 100;
    private static final int SENSOR_THRESHOLD = 25;
    Stopwatch stopwatch; // Declare stopwatch as a field
    private int detectionCount = 0;

    private EV3ColorSensor lightSensor; 
    private SampleProvider sampleProvider;
    private float[] sample;
    BackgroundMusicPlayer musicPlayer; // Declare music player as a field

    RegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.D);
    RegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
    
    
    public LineFollower(DataExchange DE) {
        DEObj = DE;
        lightSensor = new EV3ColorSensor(SensorPort.S3);
        sampleProvider = lightSensor.getRedMode();
        sample = new float[sampleProvider.sampleSize()];
        musicPlayer = new BackgroundMusicPlayer(); // Initialize music player
        stopwatch = new Stopwatch(); 
    }

    public void run() {
    	 musicPlayer.start();

        while (true) {
        	
            sampleProvider.fetchSample(sample, 0);
            int sensorValue = (int) (sample[0] * 100); // Convert sensor reading to percentage
      
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
            	 detectionCount++; // Increment detection count
                 LCD.drawString("Count: " + detectionCount, 0, 2);
                 
                 if (detectionCount >= 4) {
                     DEObj.setCMD(3); 
                     stopRobot();
                     break;
                 }
              Sound.twoBeeps();
             Sound.twoBeeps();

//Logic 1
                
                //Turn left side
               	leftMotor.setSpeed(150);
   				rightMotor.setSpeed(300);
   				leftMotor.backward();
   				rightMotor.forward();
   				Delay.msDelay(700);
   				
   				//Go forward
   				leftMotor.setSpeed(200);
   				rightMotor.setSpeed(200);
   				leftMotor.forward();
   				rightMotor.forward();
   				Delay.msDelay(500);
   				
   				//Go Right side
   				leftMotor.setSpeed(300);
   				rightMotor.setSpeed(0);
   				leftMotor.forward();
   				rightMotor.forward();
   				Delay.msDelay(900);
   						
   				//Go Forward
   				leftMotor.setSpeed(300);
   				rightMotor.setSpeed(300);
   				leftMotor.forward();
   				rightMotor.forward();
   				Delay.msDelay(1500);

   				//Go slight right side to go back on line
   				leftMotor.setSpeed(250);
   				rightMotor.setSpeed(200);
   				leftMotor.forward();
   				rightMotor.forward();
   				//Delay.msDelay(5000);
			    DEObj.setCMD(1);
   //
/*
//Logic 2
                //Turn left side
             	leftMotor.setSpeed(0);
   				rightMotor.setSpeed(300);
   				leftMotor.forward();
   				rightMotor.forward();
   				Delay.msDelay(500);
   				
   				//Go forward
   				leftMotor.setSpeed(300);
   				rightMotor.setSpeed(300);
   				leftMotor.forward();
   				rightMotor.forward();
   				Delay.msDelay(1000);
   				
   		
   				//Go slight right side to go back on line
   				leftMotor.setSpeed(300);
   				rightMotor.setSpeed(250);
   				leftMotor.forward();
   				rightMotor.forward();
			    DEObj.setCMD(1);
*/
/*Logic 3
                
                //Turn left side
               	leftMotor.setSpeed(150);
   				rightMotor.setSpeed(300);
   				leftMotor.backward();
   				rightMotor.forward();
   				Delay.msDelay(700);
   				
   				//Go forward
   				leftMotor.setSpeed(200);
   				rightMotor.setSpeed(200);
   				leftMotor.forward();
   				rightMotor.forward();
   				Delay.msDelay(500);
   				
   				//Go Right side
   				leftMotor.setSpeed(300);
   				rightMotor.setSpeed(0);
   				leftMotor.forward();
   				rightMotor.forward();
   				Delay.msDelay(900);
   						
   				//Go Forward
   				leftMotor.setSpeed(300);
   				rightMotor.setSpeed(300);
   				leftMotor.forward();
   				rightMotor.forward();
   				Delay.msDelay(1000);

   				//Go slight right side to go back on line
   				leftMotor.setSpeed(250);
   				rightMotor.setSpeed(200);
   				leftMotor.forward();
   				rightMotor.forward();
   				Delay.msDelay(2000);
   				
   				leftMotor.setSpeed(200);
   				rightMotor.setSpeed(250);
   				leftMotor.forward();
   				rightMotor.forward();
   				Delay.msDelay(700);
   				
   				leftMotor.setSpeed(200);
   				rightMotor.setSpeed(200);
   				leftMotor.forward();
   				rightMotor.forward();
   				
			    DEObj.setCMD(1);
   */
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
         /*   else if(DEObj.getCMD()==3)
            {
            	stopRobot();
            }
            */
            	
        }
    }
    
    public void stopRobot() {
        // Stop both motors
        leftMotor.stop();
        rightMotor.stop();
        
        // Stop the stopwatch and get the elapsed time
        stopwatch.elapsed();
        long elapsedTime = stopwatch.elapsed();
        LCD.drawString("Total Time: " + + elapsedTime / 1000 + " sec", 0, 3);
        LCD.drawString("Finish...",0,4);        
        musicPlayer.stopMusic();

    }
    public void stopMusic() {
        musicPlayer.stopMusic();
    }
}
