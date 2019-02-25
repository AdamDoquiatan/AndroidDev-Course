package com.adamdoq.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.adamdoq.sharedpreferences", Context.MODE_PRIVATE);

        ArrayList<String> friends = new ArrayList<>(Arrays.asList("Fido", "Joey", "Heather", "Sarah"));

        try {
            sharedPreferences.edit().putString("friends", ObjectSerializer.serialize(friends)).apply();

            Log.i("friends", ObjectSerializer.serialize(friends));

        } catch (IOException e) {
            e.printStackTrace();
        }

        //sharedPreferences.edit().putString("username", "Adam").apply();

        //String username = sharedPreferences.getString("username", "");

        //Log.i("This is the username", username);
    }
}
