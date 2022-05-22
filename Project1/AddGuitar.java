package com.example.thisproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddGuitar extends AppCompatActivity {
    @Inject
    GuitarList the_list;   // reference to singleton string list object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_guitar);
    }
    public void addItem(View view)
    {
        //create variables for each field of new Guitar
        String model;
        String serialNumber;
        int numberOfStrings;
        int numberOfFrets;
        double scaleLength;
        String fingerboard;
        double basePrice;
        String img;

        //create EditText for each variable declared
        EditText et1;
        EditText et2;
        EditText et3;
        EditText et4;
        EditText et5;
        EditText et6;
        EditText et7;
        EditText et8;

        //assign each edit text to each variable
        et1 = (EditText) findViewById(R.id.et1);
        model = et1.getText().toString();

        et2 = (EditText) findViewById(R.id.et2);
        serialNumber = et2.getText().toString();

        et3 = (EditText) findViewById(R.id.et3);
        numberOfStrings = Integer.parseInt(et3.getText().toString());

        et4 = (EditText) findViewById(R.id.et4);
        numberOfFrets = Integer.parseInt(et4.getText().toString());

        et5 = (EditText) findViewById(R.id.et5);
        scaleLength = Double.parseDouble(et5.getText().toString());

        et6 = (EditText) findViewById(R.id.et6);
        fingerboard = et6.getText().toString();

        et7 = (EditText) findViewById(R.id.et7);
        basePrice = Double.parseDouble(et7.getText().toString());

        et8 = (EditText) findViewById(R.id.et8);
        img = et8.getText().toString();

        //creates new guitar
        the_list.add(new Guitar(model, serialNumber, numberOfStrings, numberOfFrets, scaleLength, fingerboard, basePrice, img));

            Toast.makeText(AddGuitar.this,
                    "Guitar was added to the list successfully",
                    Toast.LENGTH_SHORT).show();



    } // end addItem
}