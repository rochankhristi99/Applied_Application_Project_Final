package app;

public class DataExchange {
    private boolean obstacleDetected = false;
    private int CMD = 1;
    private float blackValue; // Variable to store black value
    private float whiteValue; // Variable to store white value
    private float deviation; // Variable to store deviation

    public DataExchange() {
    }

    public void setObstacleDetected(boolean status) {
        obstacleDetected = status;
    }

    public boolean getObstacleDetected() {
        return obstacleDetected;
    }

    public void setCMD(int command) {
        CMD = command;
    }

    public int getCMD() {
        return CMD;
    }

    // Method to set black value
    public void setBlackValue(float value) {
        blackValue = value;
    }

    // Method to get black value
    public float getBlackValue() {
        return blackValue;
    }

    // Method to set white value
    public void setWhiteValue(float value) {
        whiteValue = value;
    }

    // Method to get white value
    public float getWhiteValue() {
        return whiteValue;
    }

    // Method to set deviation
    public void setDeviation(float value) {
        deviation = value;
    }

    // Method to get deviation
    public float getDeviation() {
        return deviation;
    }
}
