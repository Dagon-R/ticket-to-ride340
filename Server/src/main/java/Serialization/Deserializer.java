package Serialization;

import com.google.gson.Gson;

import Command.Command;
import Command.CommandWrapper;

public class Deserializer {
    private static Gson gson;
    static{
        gson = new Gson();
    }
    public static CommandWrapper deserializeWrapper(String comWrap){
        return gson.fromJson(comWrap, CommandWrapper.class);
    }

    public static CommandWrapper[] deserializeWrappers(String wrappers){
        return gson.fromJson(wrappers,CommandWrapper[].class);
    }
    public static Command deserializeCommand(String com, String type)throws ClassNotFoundException{
        System.out.println(type);
        return (Command)gson.fromJson(com, Class.forName(type));
    }
}
