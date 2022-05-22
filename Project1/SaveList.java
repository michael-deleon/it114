package com.example.thisproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SaveList extends AppCompatActivity {
    @Inject
    GuitarList the_list;   // reference to singleton string list object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_list);
    }
    public void saveFile(View view) throws IOException {
        EditText et1;
        et1 = (EditText) findViewById(R.id.et1);

        String infile;
        infile = et1.getText().toString();

        File outfile = new File(getExternalFilesDir(null), infile);
        FileWriter fw = new FileWriter(outfile);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        //loop that runs through the_list
        for (int i = 0; i < the_list.size(); i++){
            //write to file
            pw.write(the_list.get(i).getModel() + "\n");
            pw.write(the_list.get(i).getSerialNumber() + "\n");
            pw.write(the_list.get(i).getNumberOfStrings() + "\n");
            pw.write(the_list.get(i).getNumberOfFrets() + "\n");
            pw.write(the_list.get(i).getScaleLength() + "\n");
            pw.write(the_list.get(i).getFingerboard() + "\n");
            pw.write(the_list.get(i).getBasePrice() + "\n");
            pw.write(the_list.get(i).getImg() + "\n");
        }
        //close file
        pw.close();
        //alerts user that list has been saved
        Toast.makeText(SaveList.this,
                infile + " has been updated",
                Toast.LENGTH_SHORT).show();
    }
}//end class