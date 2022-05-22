package com.example.thisproject1;

import androidx.appcompat.app.AppCompatActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DisplayGuitarInfo extends AppCompatActivity {
    @Inject
    GuitarList the_list;   // reference to singleton string list object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_guitar_info);
    }
    public void displayGuitar(View view){
       EditText et1;
       et1 = (EditText) findViewById(R.id.edit_serial);

       String serialNumber;
       serialNumber = et1.getText().toString();

       TextView tv;
       tv = (TextView) findViewById(R.id.text_main);

       DecimalFormat df = new DecimalFormat("#.##");

       SimpleDraweeView img;

       int i;

       double salesTax = 0.06625;

       //loop that runs through each guitar
       for (i = 0; i < the_list.size(); i++){

           //matches serial number to serial number in list

           if (the_list.get(i).getSerialNumber().equals(serialNumber)){
               tv.setText("");
               tv.append("Model: " + the_list.get(i).getModel() +"\n");
               tv.append("Number of Strings: " + the_list.get(i).getNumberOfStrings() + " strings \n");
               tv.append("Number of Frets: " + the_list.get(i).getNumberOfFrets() + " frets \n");
               tv.append("Fingerboard: " + the_list.get(i).getFingerboard() + "\n");
               tv.append("Base Price: $" + df.format(the_list.get(i).getBasePrice()) + "\n");
               tv.append("Setup Cost: $" + df.format(the_list.get(i).getSetupCost()) + "\n");

               tv.append("Sales Tax: $" + df.format(((salesTax * the_list.get(i).getBasePrice()) + (salesTax * the_list.get(i).getSetupCost()))) + "\n");
               tv.append("Total Cost: $" + df.format((((salesTax * the_list.get(i).getBasePrice()) + (salesTax * the_list.get(i).getSetupCost())) + the_list.get(i).getSetupCost() + the_list.get(i).getBasePrice())) + "\n");

               img = (SimpleDraweeView) findViewById(R.id.image_area);
               Uri uri = Uri.parse(the_list.get(i).getImg());
               img.setImageURI(uri);

               break;

          }//end if
       }//end for loop
        if (i == the_list.size()){
            tv.setText("");
            Toast.makeText(DisplayGuitarInfo.this,
                    "Serial Number does not exist",
                    Toast.LENGTH_SHORT).show();
        }//end if
    }//end displayGuitar
}//end class