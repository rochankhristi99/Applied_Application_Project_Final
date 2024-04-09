package app;

/**
 * The DataExchange class handles the exchange of data between different components of the application.
 * It provides methods to set and retrieve commands.
 */
public class DataExchange {
    // The command variable to be exchanged between components
    private int CMD = 1;

    /**
     * Constructs a new DataExchange object.
     * This constructor initializes the command to a default value.
     */
    public DataExchange() {
        // Default command value
        CMD = 1;
    }

    /**
     * Sets the command value.
     * @param command The command value to be set.
     */
    public void setCMD(int command) {
        // Set the command value
        CMD = command;
    }

    /**
     * Gets the current command value.
     * @return The current command value.
     */
    public int getCMD() {
        // Return the current command value
        return CMD;
    }
}
