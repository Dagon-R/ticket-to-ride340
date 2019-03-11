package views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import Models.MainModel;
import Presenters.LoginPresenter;
import views.R;


public class MainActivity extends AppCompatActivity implements Observer { //
    EditText usernameTextfield;
    EditText passwordTextfield;
    EditText ipTextfield;
    Button loginButton;
    Button registerButton;
    LoginPresenter loginPresenter;
    MainModel mainModel;
    //SongPlayer player;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //player = new SongPlayer(MediaPlayer.create(getApplicationContext(),R.raw.railroad),true);
        //player.start();
        loginPresenter = new LoginPresenter(this);
        usernameTextfield = (EditText) findViewById(R.id.usernameTextfield);
        passwordTextfield = (EditText) findViewById(R.id.passwordTextfield);
        ipTextfield = (EditText) findViewById(R.id.ipTextfield);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerButton);
        mainModel = MainModel.get();
        mainModel.getUser().addObserver(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        //mainModel.addObserver(this);
    }

    public void login(View view){
        String username = usernameTextfield.getText().toString();
        String password = passwordTextfield.getText().toString();
        String address = ipTextfield.getText().toString();
        loginPresenter.login(username,password,address);
//        LoginService service = new LoginService();
//        service.connectToProxy(username, password, address);
//        Intent i = new Intent(this, ChooseGameActivity.class);
//        startActivity(i);
    }

    public void register(View view){
        String username = usernameTextfield.getText().toString();
        String password = passwordTextfield.getText().toString();
        String address = ipTextfield.getText().toString();
        loginPresenter.register(username,password,address);
//        if(!username.isEmpty() && !password.isEmpty() && !address.isEmpty()) {
//            RegisterService service = new RegisterService();
//            service.connectToProxy(username, password, address);
//        }
//        else{
//            Toast.makeText(this,
//                    "All fields must be filled!",
//                    Toast.LENGTH_SHORT).show();
//        }
        //Intent i = new Intent(this, ChooseGameActivity.class);
        //startActivity(i);
    }

    public void popToast(String error){
        Toast.makeText(MainActivity.this,error,Toast.LENGTH_SHORT).show();
    }

    public void update(Observable object, Object type){
//        mainModel = MainModel.get();
//        final String error = mainModel.getErrorMessage();
//        if(error != null){
//            MainActivity.this.runOnUiThread(new Runnable() {
//                public void run() {
//                    Toast.makeText(MainActivity.this,error,Toast.LENGTH_SHORT).show();
//                }
//            });
////            Toast.makeText(this,
////                    error,
////                    Toast.LENGTH_SHORT).show();
//        }
//        else{
//            User currentUser = mainModel.getUser();
//            if(currentUser.getLoggedIn()){
//                Intent i = new Intent(this, ChooseGameActivity.class);
//                startActivity(i);
//            }
//            else{
//                Toast.makeText(this,
//                        "Unexpected error!",
//                        Toast.LENGTH_SHORT).show();
//            }
//        }
    }

    public void switchToChooseGame(){
        Intent i = new Intent(this, ChooseGameActivity.class);
        startActivity(i);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.removeObserver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //player.setTurn_on(false);
        //player.start();
        loginPresenter.removeObserver();
    }

//    public void sample(View view) {
//        MainModel.get().setAuthToken("1at");
//        ServerProxy.get().register("user1","user2","1","1at");
//        ServerProxy.get().register("user2","user2","2","2at");
//        ServerProxy.get().register("user3","user3","3","3at");
//        ServerProxy.get().createGame("user1","game1");
//        ServerProxy.get().joinGame("user2","game1");
//        ServerProxy.get().joinGame("user3","game1");
//        ServerProxy.get().unPendGame("game1");
//}
}