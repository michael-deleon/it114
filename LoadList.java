package com.example.thisproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoadList extends AppCompatActivity {
    @Inject
    GuitarList the_list;   // reference to singleton string list object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_list);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
    public void loadList (View view) throws IOException {
        EditText et1;
        String inFileName;
        TextView tv;

        tv = (TextView) findViewById(R.id.text_main);

        if (!the_list.isEmpty())
            the_list.clear(); //clears out list if list is not empty

        tv.setText("");

        //try load a list via URL that user provides
        try{
            //create editText
            et1 = (EditText) findViewById(R.id.edit_file);
            inFileName = et1.getText().toString();
            URL file_url = new URL(inFileName);
            Scanner fsc = new Scanner(file_url.openStream());

            //while true, keep adding guitars from list
            while (fsc.hasNext()){
                String model = fsc.nextLine();
                String serialNumber = fsc.nextLine();
                int numberOfStrings = Integer.parseInt(fsc.nextLine());
                int numberOfFrets = Integer.parseInt(fsc.nextLine());
                double scaleLength = Double.parseDouble((fsc.nextLine()));
                String fingerboard = fsc.nextLine();
                double basePrice = Double.parseDouble((fsc.nextLine()));
                String img = fsc.nextLine();

                //add fields to create a guitar
                the_list.add(new Guitar(model, serialNumber, numberOfStrings, numberOfFrets, scaleLength, fingerboard, basePrice, img));

            }//ends while
            //tells user list was loaded
            Toast.makeText(LoadList.this,
                    "Loaded the List successfully.",
                    Toast.LENGTH_SHORT).show();
        }
        // if URL is invalid, notify user
        catch (IOException e){
        tv.append("List was invalid. Try Again.");
        }

    }
}//end class