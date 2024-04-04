package app;
import java.io.File;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.utility.Stopwatch;

public class Main {
    private static DataExchange DE;
    private static LineFollower LFObj;
    private static ObstacleDetector ODObj;
    //private static Stopwatch stopwatch;

    public static void main(String[] args) {

        System.out.println("Let's Start...");
        Sound.beepSequenceUp();   
        Button.waitForAnyPress();
        Button.LEDPattern(4);  

        // Start a thread to play the music
        Thread musicThread = new Thread(new Runnable() {
            @Override
            public void run() {
                
            }
        });
        musicThread.start();

        DE = new DataExchange();
        ODObj = new ObstacleDetector(DE);
        LFObj = new LineFollower(DE);
  //      stopwatch = new Stopwatch();

        ODObj.start();
        LFObj.start();

        
     /*   while (true) {
            if (Button.DOWN.isDown()) {
                // Stop the robot
                LFObj.stopRobot();
                
                // Get the elapsed time
                long elapsedTime = stopwatch.elapsed();

                // Display the total time on the LCD
                LCD.clear();
                LCD.drawString("Total Time: " + elapsedTime / 1000 + " sec", 0, 0);

                break; // Exit the loop
            }
        }*/
        
           
        while (Button.ENTER.isDown()) { // Continue until the down button is pressed
        	//LFObj.stopRobot();
           System.exit(0);
        }

        // Get the elapsed time
       // long elapsedTime = stopwatch.elapsed();

        // Display the total time on the LCD
      //  LCD.clear();
      //  LCD.drawString("Total Time: " + elapsedTime / 1000 + " sec", 0, 0);
     
    }
   
}
