package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;
import com.facebook.drawee.view.SimpleDraweeView;


import android.net.Uri;
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
    }
    public void displayImage(View view) throws IOException{
        TextView tv;
        EditText et1;
        String url;
        SimpleDraweeView img;
        
        et1 = (EditText) findViewById(R.id.edit_file);
        url = et1.getText().toString();
        tv = (TextView) findViewById(R.id.text_main);
        img = (SimpleDraweeView) findViewById(R.id.image_area);
        Uri uri = Uri.parse(url);
        img.setImageURI(uri);



    }
}