package com.adamdoq.braintrainer;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static final int TIME_LIMIT = 30;

    boolean gameEnabled = false;
    int totalScore = 0;
    int totalQuestions = 1;
    int answerPos;

    ConstraintLayout masterLayout;
    GridLayout infoLayout;
    GridLayout selectLayout;

    TextView countDownText;
    TextView scoreText;




    public void select(View view) {
        if(view.getTag().toString().equals("select" + (answerPos - 1) +"View")) {
            Log.i("Yes", "Right");
        } else {
            Log.i("No", "Wrong");
        }

    }

    public void startRound() {

        Log.i("Info", "hi");
        totalQuestions++;
        scoreText.setText(String.valueOf(totalScore) + "/" + String.valueOf(totalQuestions));

        Random rand = new Random();

        int num1 = rand.nextInt(20) + 1;
        int num2 = rand.nextInt(20) + 1;
        int answer = num1 + num2;

        TextView questionText = findViewById(R.id.questionText);
        questionText.setText(num1 + " + " + num2);

        answerPos = rand.nextInt(4);

        TextView select1Text = findViewById(R.id.select1Text);
        TextView select2Text = findViewById(R.id.select2Text);
        TextView select3Text = findViewById(R.id.select3Text);
        TextView select4Text = findViewById(R.id.select4Text);

        TextView[] selectTexts = {
            select1Text, select2Text, select3Text, select4Text
        };

        for(int i = 0; i < 4; i++) {
            if(i == answerPos) {
                selectTexts[i].setText(String.valueOf(answer));
            } else {
                selectTexts[i].setText(String.valueOf(rand.nextInt(41)));
            }


        }


    }


    public void startGame(View view) {
        if(!gameEnabled) {
            for (int i = 0; i < masterLayout.getChildCount(); i++) {
                View child = masterLayout.getChildAt(i);
                child.setVisibility(View.VISIBLE);
            }
            for (int i = 0; i < infoLayout.getChildCount(); i++) {
                View child = infoLayout.getChildAt(i);
                child.setVisibility(View.VISIBLE);
            }
            for (int i = 0; i < selectLayout.getChildCount(); i++) {
                View child = selectLayout.getChildAt(i);
                child.setVisibility(View.VISIBLE);
            }

            View startView = findViewById(R.id.startView);
            startView.setVisibility(View.GONE);

            gameEnabled = true;
        }

        View startText = findViewById(R.id.startInfoText);
        View replayButton = findViewById(R.id.replayButton);
        startText.setVisibility(View.INVISIBLE);
        replayButton.setVisibility(View.INVISIBLE);

        totalScore = 0;
        totalQuestions = 0;

        countDownText.setText(String.valueOf(TIME_LIMIT) + 's');
        scoreText.setText(String.valueOf(totalScore) + "/" + String.valueOf(totalQuestions));
        Log.i("Info", "hi");
        startRound();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        masterLayout = findViewById(R.id.masterLayout);
        infoLayout = findViewById(R.id.infoLayout);
        selectLayout = findViewById(R.id.selectLayout);

        countDownText = findViewById(R.id.countDownText);
        scoreText = findViewById(R.id.scoreText);
    }


}
