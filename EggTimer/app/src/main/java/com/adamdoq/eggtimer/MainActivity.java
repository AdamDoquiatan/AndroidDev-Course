package com.adamdoq.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    Button toggleButton;
    SeekBar timerBar;
    CountDownTimer timer;
    TextView countdownText;
    DecimalFormat decimalFormatter;
    MediaPlayer mediaPlayer;

    Boolean timerActive = false;



    public void toggle(View view) {
        Log.i("Button", "Clicked");

        if(timerActive) {
            toggleButton.setText("Go!");
            timerBar.setEnabled(true);
            timerActive = false;
            timer.cancel();
        } else {
            toggleButton.setText("Pause!");
            timerBar.setEnabled(false);
            timerActive = true;
            timer = new CountDownTimer((long) timerBar.getProgress(), 100) {
                @Override
                public void onTick(long millisUntilFinished) {
                    countdownText.setText(String.valueOf(decimalFormatter.format((float) millisUntilFinished / 1000))
                            .replace('.', ':'));
                    timerBar.setProgress((int) millisUntilFinished);
                }

                @Override
                public void onFinish() {
                    countdownText.setText("0:00");
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.chime);
                    mediaPlayer.start();
                }
            }.start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = findViewById(R.id.toggleButton);
        countdownText = findViewById(R.id.countdownText);
        decimalFormatter = new DecimalFormat();
        decimalFormatter.applyPattern("0.00");

        int maxTime = 60000;
        timerBar = findViewById(R.id.timerBar);

        timerBar.setMax(maxTime);
        timerBar.setProgress(0);

        timerBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("TimerBar", String.valueOf(progress));

                countdownText.setText(String.valueOf(decimalFormatter.format((float) progress / 1000))
                        .replace('.', ':'));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }
}
