package Command;

// Simple command interface
public interface Command {
    // This method uses values within the child classes to do some function unique to the
    // specific type of command
    void execute();
}
