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
    EditText passwordEditfield;
    EditText ipTextfield;
    Button loginButton;
    Button registerButton;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameTextfield = (EditText) findViewById(R.id.usernameTextfield);
        passwordEditfield = (EditText) findViewById(R.id.passwordTextfield);
        ipTextfield = (EditText) findViewById(R.id.ipTextfield);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerButton);
    }

    protected void login(){
        Intent i = new Intent(this, ChooseGameActivity.class);
        startActivity(i);
    }

    protected void register(){
        Intent i = new Intent(this, ChooseGameActivity.class);
        startActivity(i);
    }
}