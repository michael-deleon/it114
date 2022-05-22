/////////////////////////////////////////////////////////////////////////////////
// Michael Joel De Leon                                                        //
// IT114, Section 002                                                          //
// Dr. Halper                                                                  //
// App Project #1                                                              //
// March 28, 2022                                                              //
//                                                                             //
// This app is designed to the company, Jersey Guitar Emporium, manage a list  //
// of guitars available to its customers.                                      //
/////////////////////////////////////////////////////////////////////////////////
package com.example.thisproject1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;

import java.text.DecimalFormat;

import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    @Inject
    GuitarList the_list;   // reference to singleton string list object

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        tv = (TextView) findViewById(R.id.text_main);
        /*
        SAMPLE DATA
        Guitar guitar1 = new Guitar("Yamaha", "3A2-14W", 6, 21, 25.4, "Rose Wood", 544.99, "https://web.njit.edu/~halper/it114/g1.jpg");
        Guitar guitar2 = new Guitar("Gibson", "212-23A", 6, 21, 25.4, "Rose Wood", 424.00, "https://web.njit.edu/~halper/it114/g4.png");
        Guitar guitar3 = new Guitar("ESP", "565-DSF", 6, 21, 25.5, "Maple", 32524.99, "https://web.njit.edu/~halper/it114/g2.png");

        if(the_list.isEmpty())
        {

            the_list.add(the_list.size(), guitar1);
            the_list.add(the_list.size(), guitar2);
            the_list.add(the_list.size(), guitar3);

        }
         */
    }
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
    public void onOption1(MenuItem i) {
        //checks if list exists
        tv.setText("");
        if (!the_list.isEmpty()){
            // Display List

            //formats decimal to two decimal places
            DecimalFormat df = new DecimalFormat("#.##");
            //loop that goes through list
            for(int j = 0; j < the_list.size(); j++){
                tv.append(the_list.get(j).getModel()
                        + ", " + the_list.get(j).getSerialNumber()
                        + ", " + the_list.get(j).getNumberOfStrings()
                        + " strings, " + the_list.get(j).getNumberOfFrets()
                        + " frets, " + the_list.get(j).getFingerboard()
                        + ", " + the_list.get(j).getScaleLength()
                        + " inch neck, $" + df.format(the_list.get(j).getBasePrice()));
                tv.append("\n \n");//moves to new line and creates space between each guitar
            }
        }
        else{
            //lets user know list is empty
            tv.append("There are currently no guitars in the list.");
        }
    } // end onOption1

    public void onOption2(MenuItem i)
    {
        // YYY: Load list from File
        startActivity(new Intent(this, LoadList.class));

    } // end onOption2

    public void onOption3(MenuItem i)
    {
        // YYY: Add a new Guitar
            startActivity(new Intent(this, AddGuitar.class));
    } // end onOption3

    public void onOption4(MenuItem i)//option 4
    {
        // Show Guitar Details and total Cost with picture
        tv.setText("");
        //checks if list exist
        if (!the_list.isEmpty()){
            startActivity(new Intent(this, DisplayGuitarInfo.class));
        }
        else{
            //lets user know list is empty
            tv.append("There are currently no guitars in the list.");
        }

    } // end onOption4

    public void onOption5(MenuItem i)
    {
        // YYY: Remove a Guitar
        tv.setText("");

        //checks if list exist
        if (!the_list.isEmpty()){
            startActivity(new Intent(this, RemoveGuitar.class));
        }
        else{
            //lets user know list is empty
            tv.append("There are currently no guitars in the list.");
        }

    } // end onOption5

    public void onOption6(MenuItem i)
    {
        // YYY: FingerBoard Filter
        tv.setText("");

        //checks if list exist
        if (!the_list.isEmpty()){
            startActivity(new Intent(this, FingerBoardFilter.class));
        }
        else{
            //lets user know list is empty
            tv.append("There are currently no guitars in the list.");
        }

    } // end onOption6
    public void onOption7(MenuItem i)
    {
        // YYY: Display Least Expensive Guitar
        tv.setText("");

        //checks if list exist
        if(!the_list.isEmpty()){
            //formats decimal to two decimal places
            DecimalFormat df = new DecimalFormat("#.##");
            double cheapest = the_list.get(0).getBasePrice();

            //loop that goes through each index
            for (int j = 0; j < the_list.size(); j++){
                if (the_list.get(j).getBasePrice() < cheapest){
                    cheapest = the_list.get(j).getBasePrice(); //assigns the cheapest guitar
                }
            }//end for loop
            //run through list again
            for (int k = 0; k < the_list.size(); k++){
                if (cheapest == the_list.get(k).getBasePrice()){
                    tv.append("Least Expensive Guitar: " + the_list.get(k).getModel() + ", " + the_list.get(k).getSerialNumber() + ", $" + df.format(the_list.get(k).getBasePrice()));
                }//ends if
            }//end 2nd for loop

        }//end if
        else{
            //lets user know list is empty
            tv.append("There are currently no guitars in the list.");
        }



    } // end onOption7
    public void onOption8(MenuItem i)
    {
        // YYY: Display Average Base Price of the guitars
        tv.setText("");

        //checks if list exist
        if (!the_list.isEmpty()){
            //formats decimal to two decimal places
            DecimalFormat df = new DecimalFormat("#.##");

            double totalPrice = 0; //price added altogether
            int count = 0; //counter
            double averagePrice;

            //loop that goes through list
            for (int j = 0; j < the_list.size(); j++){
                //adds basePrice to totalPrice each time the loop runs
                totalPrice += the_list.get(j).getBasePrice();
                count++;
            }
            averagePrice = totalPrice/count;
            tv.append("Average Base Price: $" + df.format(averagePrice));
        }
        else{
            //lets user know list is empty
            tv.append("There are currently no guitars in the list.");
        }
    } // end onOption8
    public void onOption9(MenuItem i)
    {
        // YYY: Write to a File
        tv.setText("");

        //checks if list exist
        if (!the_list.isEmpty()){
            startActivity(new Intent(this, SaveList.class));
        }
        else{
            //lets user know list is empty
            tv.append("There are currently no guitars in the list.");
        }
    } // end onOption9

}