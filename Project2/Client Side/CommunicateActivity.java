package com.example.thisproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class CommunicateActivity extends AppCompatActivity {

    private Socket socket = null;
    private PrintWriter out = null;
    private Scanner in = null;
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communicate);

        int port;
        String hostname;

        // Get the hostname from the intent

        Intent intent = getIntent();
        hostname = intent.getStringExtra(MainActivity.HOST_NAME);

        // Get the port from the intent.  Default port is 4000

        port = intent.getIntExtra(MainActivity.PORT, 4000);

        // get a handle on the TextView for displaying the status

        tv = (TextView) findViewById(R.id.text_answer);

        // Try to open the connection to the server

        try
        {
            socket = new Socket(hostname, port);

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(new InputStreamReader(socket.getInputStream()));

            tv.setText("Connected to server.");
        }
        catch (IOException e)  // socket problems
        {
            tv.setText("Problem: " + e.toString());
            socket = null;
        }
    }//end onCreate

    public void sendParagraph(View view) throws java.io.IOException
    {
        AssetManager assetManager = getAssets();

        EditText et1;
        EditText et2;
        String inFileName;
        String user_operation;
        List<String> par = new LinkedList<>();
        int lines;

        boolean finished = false;

        // are we connected?
        if(socket == null) {
            tv.setText("Not connected.");
        }
        else {
            // get the question to send to the server (place it in "user_operation")
            et1 = (EditText) findViewById(R.id.edit_operation);
            user_operation = et1.getText().toString();

            // get the question to send to the server (place it in "inFileName")
            et2 = (EditText) findViewById(R.id.edit_file);
            inFileName = et2.getText().toString();

            Scanner fsc = new Scanner(assetManager.open(inFileName));//open file from assets folder

            //while fsc has next line
            while (fsc.hasNext()) {
                //grabs whatever is in file to add to par
                par.add(fsc.nextLine());
            }

            try {
                //send data to server
                out.println(user_operation);//sends operation
                out.println(par.size());//sends size (int)
                for (int i = 0; i < par.size(); i++) {
                    out.println(par.get(i));//sends paragraph
                }


                //receive data from server
                lines = in.nextInt();
                in.nextLine();
                //int num = in.nextInt();
                tv.append("\n" + lines + " line(s)" + "\n"); //displays line
                //for loop to display converted paragraph from server

                for (int j = 0; j < lines; j++) {
                    tv.append(in.nextLine() + "\n");
                }//end for loop


                out.close();
                in.close();
                socket.close();

                // set socket back to null to indicate that we're disconnected
                socket = null;


            } catch (IOException e) { // socket problems
                tv.setText("Problem: " + e.toString());
            }
        }
    } // end sendParagraph
}//end communicate activity