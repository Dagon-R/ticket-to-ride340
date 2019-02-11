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



public class MainActivity extends AppCompatActivity {
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
    }

    public void login(View view){
        String username = usernameTextfield.getText().toString();
        String password = passwordTextfield.getText().toString();
        String address = ipTextfield.getText().toString();
        LoginService service = new LoginService();
        service.connectToProxy(username, password, address);
        Intent i = new Intent(this, ChooseGameActivity.class);
        startActivity(i);
    }

    public void register(View view){
        String username = usernameTextfield.getText().toString();
        String password = passwordTextfield.getText().toString();
        String address = ipTextfield.getText().toString();
        RegisterService service = new RegisterService();
        service.connectToProxy(username, password, address);
        Intent i = new Intent(this, ChooseGameActivity.class);
        startActivity(i);
    }

    public void update(Observable object, Object type){
        mainModel = MainModel.get();
        String error = mainModel.getErrorMessage();
        if(error != null){
            Toast.makeText(this,
                    error,
                    Toast.LENGTH_SHORT).show();
            return;
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
}