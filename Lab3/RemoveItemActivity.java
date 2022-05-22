package com.example.stringlistlab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RemoveItemActivity extends AppCompatActivity {

    @Inject
    StringList the_list;   // reference to singleton string list object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_item);
    }
    // remove item from the list

    public void removeItem(View view)
    {

        EditText et1;
        int pos;

        // get item and its desired position

        et1 = (EditText) findViewById(R.id.edit_item);
        pos = Integer.parseInt(et1.getText().toString());

        // try to remove the item on the list

        try
        {
            the_list.remove(pos);

            Toast.makeText(RemoveItemActivity.this,
                    pos + " removed from the list",
                    Toast.LENGTH_SHORT).show();
        }
        catch(IndexOutOfBoundsException e)
        {
            Toast.makeText(RemoveItemActivity.this,
                    "Failed to remove item from the list",
                    Toast.LENGTH_SHORT).show();

        }

    } // end removeItem

} // end RemoveItemActivity

