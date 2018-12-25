package com.adamdoq.timestables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = findViewById(R.id.listView);

        final ArrayList<Integer> intList = new ArrayList<>(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        );

        final ArrayAdapter<Integer> myAdapter = new ArrayAdapter<>(
                getApplicationContext(), android.R.layout.simple_list_item_1, intList);

        listView.setAdapter(myAdapter);

        SeekBar seekBar = findViewById(R.id.seekBar);

        seekBar.setMax(intList.size());
        seekBar.setProgress(1);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min = 1;
                int timesVariable;

                if(progress < min) {
                    timesVariable = min;
                    seekBar.setProgress(min);
                } else {
                    timesVariable = progress;
                }


                for(int i = 1; i <= intList.size() ; i++) {
                    intList.set(i - 1, timesVariable * i);
                }

                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });








    }
}
