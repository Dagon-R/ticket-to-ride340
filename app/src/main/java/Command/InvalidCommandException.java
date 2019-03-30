package Command;

// Class to represent when a socket cannot parse a JSON string into a command
public class InvalidCommandException extends Exception {
    @Override // Overrides the print value of the exception
    public void printStackTrace() {
        System.out.print("Socket stream un-parsable (likely forgot comma at end)");
    }
}
