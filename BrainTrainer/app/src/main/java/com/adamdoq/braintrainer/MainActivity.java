package com.adamdoq.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static final int TIME_LIMIT = 30;

    private boolean gameEnabled = false;
    private int totalScore = 0;
    private int totalQuestions = 0;
    private int answerPos;

    ConstraintLayout masterLayout;
    GridLayout infoLayout;
    GridLayout selectLayout;

    TextView countDownText;
    TextView scoreText;
    TextView feedbackText;

    public void select(View view) {
        feedbackText.setVisibility(View.VISIBLE);

        if(view.getTag().toString().equals("select" + (answerPos + 1) + "View")) {
            feedbackText.setText("Right! :D");
            totalScore++;
        } else {
            feedbackText.setText("Wrong! D:");
        }
        startRound();
    }

    public void startRound() {
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

        final View feedbackText = findViewById(R.id.feedbackText);
        View replayButton = findViewById(R.id.replayButton);
        feedbackText.setVisibility(View.INVISIBLE);
        replayButton.setVisibility(View.INVISIBLE);

        totalScore = 0;
        totalQuestions = 0;

        countDownText.setText(String.valueOf(TIME_LIMIT) + 's');
        scoreText.setText(String.valueOf(totalScore) + "/" + String.valueOf(totalQuestions));

        new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                countDownText.setText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                TextView feedbackText = findViewById(R.id.feedbackText);
                feedbackText.setText("You got: " + totalScore + "/" + totalQuestions + " right!");
                Button replayButton = findViewById(R.id.replayButton);
                replayButton.setVisibility(View.VISIBLE);
                replayButton.setEnabled(true);
            }
        }.start();

        startRound();
    }

    public void replay(View view) {
        view.setEnabled(false);
        startGame(view);
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
        feedbackText = findViewById(R.id.feedbackText);
    }


}
