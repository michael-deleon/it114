package com.example.ourlab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void processPress(View view)
            throws java.io.IOException {
        AssetManager assetManager = getAssets();
        TextView tv;
        EditText et1;
        EditText et2;
        String inFileName;
        String outFileName;

        tv = (TextView) findViewById(R.id.text_main);


        // get the values from the EditTexts and
        // display them

        et1 = (EditText) findViewById(R.id.edit_infile);
        et2 = (EditText) findViewById(R.id.edit_outfile);
        inFileName = et1.getText().toString();
        outFileName = et2.getText().toString();

        File outfile = new File(getExternalFilesDir(null),outFileName);
        FileWriter fw = new FileWriter(outfile);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        //array things
        double[] arr = new double[20];
        int n = 0;//keeps track of array placement

        try {
            Scanner fsc = new Scanner(assetManager.open(inFileName));

            while (fsc.hasNext()) {
                arr[n] = fsc.nextDouble();
                n++;

            }
        } catch (FileNotFoundException e) {
            tv.append("File does not exist. Try again.");
        }
        tv.append("\n");
        tv.append("Input file's name is: " + inFileName + "\n");
        tv.append("Output file's name is: " + outFileName + "\n");

        abs_distance_mean(arr, n);

        for(int i = 0; i < n; i++)
        {
            pw.println(String.format("%.5f",arr[i]));
            tv.append(String.format("%.5f", arr[i])+"\n");
        }
        pw.close();

    }// end processPress

    public void abs_distance_mean(double[] a, int num_vals) {
        AssetManager assetManager = getAssets();
        // you write this. Use Math.abs() in this method
        TextView tv;
        double m = 0; //average
        tv = (TextView) findViewById(R.id.text_main);
        for (int i = 0; i < num_vals; i++){
            m = m + a[i];
        }
            m = m/num_vals;
        for (int j = 0; j < a.length; j++) {
            a[j] = (Math.round(Math.abs(a[j] - m)*100000.0)/100000.0);

        }
    }// end abs_distance_mean
}