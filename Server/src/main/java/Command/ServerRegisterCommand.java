package Command;

import java.util.Objects;

import Services.RegisterService;
import Services.Service;

public class ServerRegisterCommand implements Command{
    private String username;
    private String password;
    private boolean valid;
    private String ipAddress;
    public ServerRegisterCommand() {
    }

    public ServerRegisterCommand(String username, String password, String ipAddress) {
        this.username = username;
        this.password = password;
        this.ipAddress = ipAddress;
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
        if(obj.getClass() != Boolean.class) return;
        Boolean val = (Boolean) obj;
        valid = val;
    }

    @Override
    public Object execute() {
        Service registerService = new RegisterService();
        return registerService.doService(username, password);
    }

    @Override
    public String toString() {
        return "ServerRegisterCommand{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", valid=" + valid +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServerRegisterCommand)) return false;
        ServerRegisterCommand that = (ServerRegisterCommand) o;
        return valid == that.valid &&
                Objects.equals(getUsername(), that.getUsername()) &&
                Objects.equals(getPassword(), that.getPassword()) &&
                Objects.equals(getIpAddress(), that.getIpAddress());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUsername(), getPassword(), valid, getIpAddress());
    }
}
