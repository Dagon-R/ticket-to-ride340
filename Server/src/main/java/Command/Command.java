package Command;

public interface Command {

    Object execute(String gameID);

    void addResults(Object obj);

    void setIpAddress(String ipAddress);

}
