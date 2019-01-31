package Serialization;

import com.google.gson.Gson;

import Commands.Command;
import Commands.CommandWrapper;

public class Deserializer {
    private static Gson gson;
    static{
        gson = new Gson();
    }
    public static CommandWrapper deserializeWrapper(String comWrap){
        return gson.fromJson(comWrap, CommandWrapper.class);
    }
    public static Command deserializeCommand(String com, String type)throws ClassNotFoundException{
        return (Command)gson.fromJson(com, Class.forName(type));
    }
}
