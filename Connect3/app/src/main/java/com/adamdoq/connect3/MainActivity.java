package com.adamdoq.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    boolean winnerFound = false;

    String currentTurn = "red";

    Map<String, String> boardPos = new HashMap<>();

    private void populateHashMap() {
        boardPos.put("pos1", "empty");
        boardPos.put("pos2", "empty");
        boardPos.put("pos3", "empty");
        boardPos.put("pos4", "empty");
        boardPos.put("pos5", "empty");
        boardPos.put("pos6", "empty");
        boardPos.put("pos7", "empty");
        boardPos.put("pos8", "empty");
        boardPos.put("pos9", "empty");
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
        if (!winnerFound) {
            ImageView position = (ImageView) view;

            if (boardPos.get(position.getTag().toString()).equals("empty")) {
                if (currentTurn.equals("red")) {
                    position.setImageResource(R.drawable.red);
                    boardPos.put(position.getTag().toString(), "red");
                    currentTurn = "yellow";
                } else {
                    position.setImageResource(R.drawable.yellow);
                    boardPos.put(position.getTag().toString(), "yellow");
                    currentTurn = "red";
                }
                position.setTranslationY(-2000);
                position.animate().translationYBy(2000).setDuration(350);

                checkWin();
            }
        }
    }

    private void checkWin() {
        String checkColor = "red";

        for (int i = 0; i < 2; i++) {
            if (boardPos.get("pos1").equals(checkColor)
                    && boardPos.get("pos2").equals(checkColor)
                    && boardPos.get("pos3").equals(checkColor)) {
                victory(checkColor);
            } else if (boardPos.get("pos4").equals(checkColor)
                    && boardPos.get("pos5").equals(checkColor)
                    && boardPos.get("pos6").equals(checkColor)) {
                victory(checkColor);
            } else if (boardPos.get("pos7").equals(checkColor)
                    && boardPos.get("pos8").equals(checkColor)
                    && boardPos.get("pos9").equals(checkColor)) {
                victory(checkColor);
            } else if (boardPos.get("pos1").equals(checkColor)
                    && boardPos.get("pos4").equals(checkColor)
                    && boardPos.get("pos7").equals(checkColor)) {
                victory(checkColor);
            } else if (boardPos.get("pos2").equals(checkColor)
                    && boardPos.get("pos5").equals(checkColor)
                    && boardPos.get("pos8").equals(checkColor)) {
                victory(checkColor);
            } else if (boardPos.get("pos3").equals(checkColor)
                    && boardPos.get("pos6").equals(checkColor)
                    && boardPos.get("pos9").equals(checkColor)) {
                victory(checkColor);
            } else if (boardPos.get("pos1").equals(checkColor)
                    && boardPos.get("pos5").equals(checkColor)
                    && boardPos.get("pos9").equals(checkColor)) {
                victory(checkColor);
            } else if (boardPos.get("pos3").equals(checkColor)
                    && boardPos.get("pos5").equals(checkColor)
                    && boardPos.get("pos7").equals(checkColor)) {
                victory(checkColor);
            }
            checkColor = "yellow";
        }
        checkColor = "red";
    }

    private void victory(String winner) {
        winnerFound = true;
        TextView victoryText = findViewById(R.id.victoryText);
        victoryText.setAlpha(1);
        victoryText.setText("" + winner + " wins!");

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setAlpha(1);
        resetButton.setEnabled(true);
    }

    public void reset(View view) {
        TextView victoryText = findViewById(R.id.victoryText);
        victoryText.setAlpha(0);

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setAlpha(0);
        resetButton.setEnabled(false);

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView childPos = (ImageView) gridLayout.getChildAt(i);
            childPos.setImageDrawable(null);
        }

        for(int i = 0; i < 9; i++) {
            boardPos.put("pos" + i, "empty");
        }

        winnerFound = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateHashMap();
        setTags();
    }
}
