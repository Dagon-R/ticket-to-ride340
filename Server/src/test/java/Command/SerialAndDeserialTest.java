package Command;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import Serialization.Deserializer;
//import Serialization.Serializer;
//
//import static org.junit.Assert.*;
//
//public class SerialAndDeserialTest {
//    private Command createGame;
//    private Command joinGame;
//    private Command login;
//    private Command register;
//    private Command startGame;
//
//    @Before
//    public void setUp(){
//        createGame = new ServerCreateGameCommand("awesomeGame");
//        joinGame = new ServerJoinGameCommand("awesomeGame");
//        login = new ServerTheLoginCommand("user","password");
//        register = new ServerRegisterCommand("user","password");
//        startGame = new ServerCreateGameCommand("awesomeGame");
//
//    }
//    @Test
//    public void serialize(){
//        //Test if it is being serialized
//        wrapAndTest(createGame);
//        wrapAndTest(joinGame);
//        wrapAndTest(login);
//        wrapAndTest(register);
//        wrapAndTest(startGame);
//
//
//
//    }
//
//    private void wrapAndTest(Command c){
//        CommandWrapper commandWrapper = new CommandWrapper(Serializer.serialize(c),c.getClass().getName());
//        String res = Serializer.serialize(commandWrapper);
//        assertNotEquals(res.length(), 0);
//        System.out.println(res);
//        deserialize(res,c);
//    }
//
//    private void deserialize(String res,Command c){
//
//        CommandWrapper commandWrapper = Deserializer.deserializeWrapper(res);
//        try{
//            Command command = (Command)Deserializer.deserializeCommand(commandWrapper.getCommand(),commandWrapper.getType());
//            assertEquals(command,c);
//        }
//        catch(ClassNotFoundException e){
//            assertNotEquals(true, false);
//            System.out.println(e.getMessage());
//        }
//
//    }
//}