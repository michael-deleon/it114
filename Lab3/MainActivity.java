package com.example.stringlistlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Collections;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

/////////////////////////////////////////////////////
//
// This app manipulates a list of strings.  Options
// are chosen from the main app bar.
//
// Some options just display results in the main
// TextView.  Others invoke new activities to carry
// out their tasks.
//
// Author: M. Halper
//
/////////////////////////////////////////////////////

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Inject
    StringList the_list;   // reference to singleton string list object

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // set the reference to the "main" TextView object so
        // we do not have to retrieve it in every method below

        tv = (TextView) findViewById(R.id.text_main);

        // put some strings on the list (if the list is empty).  Note that the
        // "new" list might not be empty due to a restart of the app

        if(the_list.isEmpty())
        {

            the_list.add(the_list.size(), "pizza");
            the_list.add(the_list.size(), "crackers");
            the_list.add(the_list.size(), "peanut butter");
            the_list.add(the_list.size(), "jelly");
            the_list.add(the_list.size(), "bread");
            the_list.add(the_list.size(), "spaghetti");

        }

    } // end onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onOption1(MenuItem i)
    {
        // show the list in order

        int j;

        tv.setText("");

        // display the list items

        for(j = 0; j < the_list.size(); j++)

            tv.append(the_list.get(j) + '\n');

    } // end onOption1

    public void onOption2(MenuItem i) //Reverse
    {
        // YYY: show the list in reverse order
        tv.setText("Displaying the list in reverse order. \n");


        //reverseList = the_list;
        Collections.reverse(the_list);
        int j;
        for(j = 0; j < the_list.size(); j++)
            tv.append(the_list.get(j) + '\n');


    } // end onOption2

    public void onOption3(MenuItem i)//Display ListSize
    {
        // YYY: show the list size

        tv.setText("Displaying the size of the list. \n");

        tv.append("List size: "+ the_list.size());

    } // end onOption3

    public void onOption4(MenuItem i)//option 4
    {

        // Start the activity to add a new item to the list

        startActivity(new Intent(this, AddItemActivity.class));

    } // end onOption4

    public void onOption5(MenuItem i)
    {

        // YYY: Start the activity to remove an item from the list

        startActivity(new Intent(this, RemoveItemActivity.class));


    } // end onOption5

    public void onOption6(MenuItem i)
    {

        // YYY: Remove all items from the list

        tv.setText("Removing all items from the list.");

        the_list.clear();

    } // end onOption6

} // end MainActivity
