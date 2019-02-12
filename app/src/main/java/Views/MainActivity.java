package Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import Models.ClientGameList;
import Models.MainModel;
import Models.PendingGame;
import Models.Player;
import Models.PlayerColorEnum;
import Models.User;
import Services.LoginService;
import Services.RegisterService;


public class MainActivity extends AppCompatActivity implements Observer {
    EditText usernameTextfield;
    EditText passwordTextfield;
    EditText ipTextfield;
    Button loginButton;
    Button registerButton;

    MainModel mainModel;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameTextfield = (EditText) findViewById(R.id.usernameTextfield);
        passwordTextfield = (EditText) findViewById(R.id.passwordTextfield);
        ipTextfield = (EditText) findViewById(R.id.ipTextfield);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerButton);
        mainModel = MainModel.get();
        mainModel.getUser().addObserver(this);
    }

    public void login(View view){
        String username = usernameTextfield.getText().toString();
        String password = passwordTextfield.getText().toString();
        String address = ipTextfield.getText().toString();
        LoginService service = new LoginService();
        service.connectToProxy(username, password, address);
//        Intent i = new Intent(this, ChooseGameActivity.class);
//        startActivity(i);
    }

    public void register(View view){
        String username = usernameTextfield.getText().toString();
        String password = passwordTextfield.getText().toString();
        String address = ipTextfield.getText().toString();
        if(!username.isEmpty() && !password.isEmpty() && !address.isEmpty()) {
            RegisterService service = new RegisterService();
            service.connectToProxy(username, password, address);
        }
        else{
            Toast.makeText(this,
                    "All fields must be filled!",
                    Toast.LENGTH_SHORT).show();
        }
        //Intent i = new Intent(this, ChooseGameActivity.class);
        //startActivity(i);
    }

    public void update(Observable object, Object type){
        mainModel = MainModel.get();
        final String error = mainModel.getErrorMessage();
        if(error != null){
            MainActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(MainActivity.this,error,Toast.LENGTH_SHORT).show();
                }
            });
//            Toast.makeText(this,
//                    error,
//                    Toast.LENGTH_SHORT).show();
        }
        else{
            User currentUser = mainModel.getUser();
            if(currentUser.getLoggedIn()){
                Intent i = new Intent(this, ChooseGameActivity.class);
                startActivity(i);
            }
            else{
                Toast.makeText(this,
                        "Unexpected error!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainModel.deleteObserver(this);
    }
}