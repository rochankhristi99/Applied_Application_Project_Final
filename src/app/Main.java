package app;
import lejos.hardware.Button;
import lejos.hardware.Sound;

public class Main {
    private static DataExchange DE;
    private static LineFollower LFObj;
    private static ObstacleDetector ODObj;

    public static void main(String[] args) {
		System.out.println("Let's Start...");
		Sound.beepSequenceUp();   
        //Button.waitForAnyPress();
        
        DE = new DataExchange();
        ODObj = new ObstacleDetector(DE);
        LFObj = new LineFollower(DE);

        ODObj.start();
        LFObj.start();

        while (!Button.ENTER.isDown()) {
            // Empty
        }

        System.exit(0);
    }
}
