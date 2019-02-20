package Views;



import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import Models.CityLoc;


public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Gson gson = new Gson();
        CityLoc[] cityLoc = null;
//        File file = new File("./app/src/main/java/Models/usdata.txt");
        try{
            InputStream stream = getResources().openRawResource(R.raw.usdata);
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            StringBuilder s = new StringBuilder();
            while(br.ready()){
                s.append(br.readLine());
            }
            cityLoc = gson.fromJson(s.toString(),CityLoc[].class);

        }
        catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }


        setContentView(new MapLogic(this,cityLoc));

    }

}
