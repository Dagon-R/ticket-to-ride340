package Command;

public class InvalidCommandException extends Exception {
    @Override
    public void printStackTrace() {
        System.out.print("Socket stream un-parsable (likely forgot comma at end)");
    }
}
