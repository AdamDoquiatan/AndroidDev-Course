package com.adamdoq.currencycoverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    public void convert(View view) {
      //  Log.i("info", "It works!");

        TextView amountCADView = (TextView) findViewById(R.id.amountToCovert);
        int amountCADInt = Integer.parseInt(amountCADView.getText().toString());

        NumberFormat formatter = new DecimalFormat("#.00");

        Toast.makeText(this, "" + amountCADInt + " CAD = " + formatter.format((amountCADInt * 1.72)) + " British Pounds" , Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
