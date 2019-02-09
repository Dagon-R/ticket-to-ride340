package Command;

public interface Command {

    Object execute();

    void addResults(Object obj);
}
