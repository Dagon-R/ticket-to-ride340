package Serialization;

import com.google.gson.Gson;

public class Serializer {
    private static Gson gson;
    static{
        gson = new Gson();
    }
    public static String serialize(Object obj){
        return gson.toJson(obj);
    }

}
