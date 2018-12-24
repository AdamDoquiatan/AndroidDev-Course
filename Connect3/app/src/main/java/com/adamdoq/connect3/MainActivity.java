package com.adamdoq.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String currentTurn = "red";

    Map<String, String> turnsTaken = new HashMap<>();

    private void populateHashMap() {
        turnsTaken.put("pos1", "empty");
        turnsTaken.put("pos2", "empty");
        turnsTaken.put("pos3", "empty");
        turnsTaken.put("pos4", "empty");
        turnsTaken.put("pos5", "empty");
        turnsTaken.put("pos6", "empty");
        turnsTaken.put("pos7", "empty");
        turnsTaken.put("pos8", "empty");
        turnsTaken.put("pos9", "empty");
    }

    private void setTags() {
        findViewById(R.id.pos1).setTag("pos1");
        findViewById(R.id.pos2).setTag("pos2");
        findViewById(R.id.pos3).setTag("pos3");
        findViewById(R.id.pos4).setTag("pos4");
        findViewById(R.id.pos5).setTag("pos5");
        findViewById(R.id.pos6).setTag("pos6");
        findViewById(R.id.pos7).setTag("pos7");
        findViewById(R.id.pos8).setTag("pos8");
        findViewById(R.id.pos9).setTag("pos9");
    }



    public void handlePos(View view) {
        ImageView position = (ImageView) view;

        if(turnsTaken.get(position.getTag().toString()).equals("empty")) {
           if (currentTurn.equals("red")) {
               position.setImageResource(R.drawable.red);
               turnsTaken.put(position.getTag().toString(), "red");
               currentTurn = "yellow";
           } else {
               position.setImageResource(R.drawable.yellow);
               turnsTaken.put(position.getTag().toString(), "yellow");
               currentTurn = "red";
           }
           position.setTranslationY(-2000);
           position.animate().translationYBy(2000).setDuration(350);
       }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateHashMap();
        setTags();
    }
}
