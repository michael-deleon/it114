package com.example.stringlistlab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddItemActivity extends AppCompatActivity {

    @Inject
    StringList the_list;   // reference to singleton string list object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
    }

    // add the item to the list

    public void addItem(View view)
    {

        EditText et1;
        EditText et2;
        String new_item;
        int pos;

        // get the new item and its desired position

        et1 = (EditText) findViewById(R.id.edit_item);
        new_item = et1.getText().toString();

        et2 = (EditText) findViewById(R.id.edit_position);
        pos = Integer.parseInt(et2.getText().toString());

        // try to put the new item on the list

        try
        {
            the_list.add(pos, new_item);

            Toast.makeText(AddItemActivity.this,
                    new_item + " added to the list",
                    Toast.LENGTH_SHORT).show();
        }
        catch(IndexOutOfBoundsException e)
        {
            Toast.makeText(AddItemActivity.this,
                    "Failed to add item to the list",
                    Toast.LENGTH_SHORT).show();

        }

    } // end addItem

} // end AddItemActivity
