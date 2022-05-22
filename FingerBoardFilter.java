package com.example.thisproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FingerBoardFilter extends AppCompatActivity {
    @Inject
    GuitarList the_list;   // reference to singleton string list object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_board_filter);
    }
    public void filter(View view) {

        EditText et1;
        String wood;
        TextView tv;

        et1 = (EditText) findViewById(R.id.edit_wood);
        wood = et1.getText().toString();
        tv = (TextView) findViewById(R.id.text_main);

        DecimalFormat df = new DecimalFormat("#.##");

        int i;

        tv.setText("");
        //loop that runs through each guitar
        for (i = 0; i < the_list.size(); i++) {
            //matches wood to guitars that have finger board wood in list
            if (the_list.get(i).getFingerboard().equals(wood)) {
                //displays guitar data
                tv.append(the_list.get(i).getModel()
                        + ", " + the_list.get(i).getSerialNumber()
                        + ", " + the_list.get(i).getNumberOfStrings()
                        + " strings, " + the_list.get(i).getNumberOfFrets()
                        + " frets, $" + df.format(the_list.get(i).getBasePrice()));
                tv.append("\n \n");//moves to new line and creates space between each guitar

            }//end if
        }//end for loop
    }
}//end class