package app;

import lejos.hardware.Sound;

/**
 * The BackgroundMusicPlayer class handles playing background music using the LeJOS Sound API.
 * It extends Thread to allow playing music in a separate thread.
 */
public class BackgroundMusicPlayer extends Thread {
    // Arrays storing music notes and their durations
    private int[] NOTES = {440, 493, 392, 392, 493, 493, 493, 493, 493, 493, 493, 493, 392, 392, 440, 440, 440, 440, 440, 440};
    private int[] DURATIONS = {200, 200, 300, 300, 200, 100, 100, 200, 200, 200, 200, 200, 300, 300, 200, 100, 100, 200, 200, 200};

    // Flag to control music
    private volatile boolean keepPlaying = true;

    /**
     * The run method runs the music loop until keepPlaying is set to false.
     */
    public void run() {
        // Set the volume for playing music
        Sound.setVolume(10);

        // Music loop
        while (keepPlaying) {
            // Play each note with its corresponding duration
            for (int i = 0; i < NOTES.length && keepPlaying; i++) {
                Sound.playTone(NOTES[i], DURATIONS[i]);
            }
        }
    }

    /**
     * Stops the background music..
     */
    public void stopMusic() {
        // Set keepPlaying flag to false to stop music
        keepPlaying = false;
    }
    
}
