package app;

import lejos.hardware.Sound;

public class BackgroundMusicPlayer extends Thread {
    private static final int[] NOTES = {392, 392, 440, 392, 349, 330, 294}; // Example notes
    private static final int[] DURATIONS = {400, 200, 400, 400, 400, 400, 800}; // Note durations (milliseconds)
    private volatile boolean keepPlaying = true; // Flag to control music playback

    public void run() {
        Sound.setVolume(10); // Adjust volume as needed

        // Play the melody continuously until instructed to stop
        while (keepPlaying) {
            for (int i = 0; i < NOTES.length && keepPlaying; i++) {
                Sound.playTone(NOTES[i], DURATIONS[i]);
            }
        }
    }

    // Method to stop the music playback
    public void stopMusic() {
        keepPlaying = false;
    }
}
