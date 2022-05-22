package com.example.qaclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CommunicateActivity extends AppCompatActivity {

    private Socket socket = null;
    private PrintWriter out = null;
    private Scanner in = null;
    private TextView tv;

    // In onCreate, connect to the server, and then wait for the
    // user to input the question and press the button

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

    } // end onCreate

    public void sendQuestion(View view)
    {
        EditText et;
        String user_question;
        String answer;
        boolean finished = false;

        // are we connected?

        if(socket == null)
        {
            tv.setText("Not connected.");
        }
        else
        {
            // get the question to send to the server (place it in "user_question")

            // YYY

            et = (EditText) findViewById(R.id.edit_question);
            user_question = et.getText().toString();

            // if the (input) question is "quit", we're finished; let
            // the server know by sending it "quit".  Also, don't forget
            // to "raise the flag" locally.  Otherwise, just send the
            // question and get a response

            if (user_question == "quit") // YYY: write proper condition: replace "true"
            {
                // YYY (two statements here)

                out.println(user_question);
                finished = true;

            }
            else
            {
                // send question to server

                // YYY
                out.println(user_question);

                // read response (into answer) and display it

                // YYY
                answer = in.nextLine();

                tv.setText("Answer: " + answer);
            }

            // if we're finished, close the connection

            if(finished)
            {
                try
                {
                    out.close();
                    in.close();
                    socket.close();

                    // set socket back to null to indicate that we're disconnected

                    socket = null;

                    tv.setText("Finished.  Connection closed.");
                }
                catch (IOException e)  // socket problems
                {
                    tv.setText("Problem: " + e.toString());
                }

            }

        }

    } // end sendQuestion

} // end CommunicateActivity
