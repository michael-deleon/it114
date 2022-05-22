package com.example.thisproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RemoveGuitar extends AppCompatActivity {
    @Inject
    GuitarList the_list;   // reference to singleton string list object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_guitar);
    }
    // remove item from the list

    public void removeItem(View view)
    {

        EditText et1;
        String serialNumber;
        TextView tv;

        et1 = (EditText) findViewById(R.id.edit_item);
        serialNumber = et1.getText().toString();
        tv = (TextView) findViewById(R.id.text_main);

        int i;

        //loop that runs through each guitar
        for (i = 0; i < the_list.size(); i++){
            //matches serial number to serial number in list
            if (the_list.get(i).getSerialNumber().equals(serialNumber)){
                the_list.remove(i); //removes guitar where serial number matches
                Toast.makeText(RemoveGuitar.this,
                        "Guitar was removed successfully.",
                        Toast.LENGTH_SHORT).show();
                break; //breaks loop

            }//end if
        }//end for loop
        if (i == the_list.size()){
            tv.setText("");
            Toast.makeText(RemoveGuitar.this,
                    "Serial Number does not exist",
                    Toast.LENGTH_SHORT).show();
        }//end if
    }//end removeItem
}