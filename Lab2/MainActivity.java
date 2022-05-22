package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }//ends method

    public void displayHouses(View view) throws IOException
    {
        TextView tv;
        EditText et1;
        String url;

        double averageSquareFoot;
        double weightedAverage;

        et1 = (EditText) findViewById(R.id.edit_file);
        url = et1.getText().toString();
        tv = (TextView) findViewById(R.id.text_main);

        URL file_url = new URL(url);


        Scanner fsc = new Scanner(file_url.openStream());

        //house1
        House firstHouse = new House(fsc.nextLine(), fsc.nextLine(),fsc.nextDouble(),fsc.nextInt(),fsc.nextDouble(),fsc.nextInt());
        fsc.nextLine(); //get next Int
            tv.append("\n First House \n");
            tv.append("Street Address: " + firstHouse.getAddress() + "\n");
            tv.append("City: " + firstHouse.getCity() + "\n");
            tv.append("Value: " + firstHouse.getAssessedValue() + "\n");
            tv.append("Year Built: " + firstHouse.getYear() +"\n");
            tv.append("Property Tax: " + firstHouse.getTax() + "\n");
            tv.append("Square Footage: " + firstHouse.getSquareFootage() +"\n");
            tv.append("\n");
        //house2
        House secondHouse = new House(fsc.nextLine(), fsc.nextLine(),fsc.nextDouble(),fsc.nextInt(),fsc.nextDouble(),fsc.nextInt());
        fsc.nextLine(); //get next Int
            tv.append("Second House \n");
            tv.append("Street Address: " + secondHouse.getAddress() + "\n");
            tv.append("City: " + secondHouse.getCity() + "\n");
            tv.append("Value: " + secondHouse.getAssessedValue() +"\n");
            tv.append("Year Built: " + secondHouse.getYear() + "\n");
            tv.append("Property Tax: " + secondHouse.getTax() + "\n");
            tv.append("Square Footage: " + secondHouse.getSquareFootage() + "\n");
        tv.append("\n");//new line
        //house3
        House thirdHouse = new House(fsc.nextLine(), fsc.nextLine(),fsc.nextDouble(),fsc.nextInt(),fsc.nextDouble(),fsc.nextInt());
        fsc.nextLine();//get next Int
            tv.append("Third House \n");
            tv.append("Street Address: " + thirdHouse.getAddress() + "\n");
            tv.append("City: " + thirdHouse.getCity() + "\n");
            tv.append("Value: " + thirdHouse.getAssessedValue() + "\n");
            tv.append("Year Built: " + thirdHouse.getYear() + "\n");
            tv.append("Property Tax: " + thirdHouse.getTax() + "\n");
            tv.append("Square Footage: " + thirdHouse.getSquareFootage() + "\n");


        tv.append("\n");//new line

            averageSquareFoot = (firstHouse.getSquareFootage() + secondHouse.getSquareFootage() + thirdHouse.getSquareFootage())/3;
            tv.append("Average Square Footage: " + String.format("%.3f",averageSquareFoot) + "\n \n"); //Displays and formats

            weightedAverage = ((firstHouse.getTax()* firstHouse.getSquareFootage()) + (secondHouse.getTax()* secondHouse.getSquareFootage()) + (thirdHouse.getTax()* thirdHouse.getSquareFootage()))/ (firstHouse.getSquareFootage()+secondHouse.getSquareFootage()+ thirdHouse.getSquareFootage());
            tv.append("Weighted Average of Property Taxes: $"  + String.format("%.2f",weightedAverage)); //Displays and formats
            tv.append("\n");


    }//end of displayHouses
}//end of class