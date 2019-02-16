package Phase2Models;

public class InvalidStoreLengthException extends Exception {
    @Override
    public String getMessage() {
        return "Store not initialized to an array of five cards";
    }
}
