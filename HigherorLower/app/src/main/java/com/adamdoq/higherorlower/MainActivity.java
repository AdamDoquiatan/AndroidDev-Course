package com.adamdoq.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random rand = new Random();

    int compNum = rand.nextInt(100) + 1;

    public void guess(View view) {
        // Log.i("info", "It works!");

        EditText userText = (EditText) findViewById(R.id.guessField);
        int userNum = Integer.parseInt(userText.getText().toString());

        if(userNum > compNum) {
            Toast.makeText(this, "Lower pls", Toast.LENGTH_SHORT).show();
        } else if(userNum < compNum) {
            Toast.makeText(this, "Higher pls", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "You got it! My number was " + compNum + "\nPlay again!", Toast.LENGTH_SHORT).show();
            compNum = rand.nextInt(100) + 1;
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
