package Command;

import java.util.Objects;

import Models.GameList;
import Services.LoginService;
import Services.Service;

public class ServerTheLoginCommand implements Command{
    private String username;
    private String password;
    private boolean valid;
    private String ipAddress;
    private GameList gameList;
    private String authToken;
    public ServerTheLoginCommand() {
    }

    public ServerTheLoginCommand(String username, String password, boolean valid, String ipAddress) {
        this.username = username;
        this.password = password;
        this.valid = valid;
        this.ipAddress = ipAddress;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public void addResults(Object obj) {
        if(obj == null) return;
        if(obj.getClass() != GameList.class) return;
        GameList gameList = (GameList) obj;
        this.gameList = gameList;
    }

    @Override
    public Object execute() {
        //pass in info in constructor
        Service loginService = new LoginService();
        return loginService.doService(username,password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServerTheLoginCommand)) return false;
        ServerTheLoginCommand that = (ServerTheLoginCommand) o;
        return Objects.equals(getUsername(), that.getUsername()) &&
                Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUsername(), getPassword());
    }


    @Override
    public String toString() {
        return "ServerTheLoginCommand{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", valid=" + valid +
                ", ipAddress='" + ipAddress + '\'' +
                ", gameList=" + gameList +
                ", authToken='" + authToken + '\'' +
                '}';
    }
}
