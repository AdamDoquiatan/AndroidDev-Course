package com.adamdoq.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void fade(View view) {
        ImageView bartImageView = findViewById(R.id.bartImageView);
        ImageView homerImageView = findViewById(R.id.homerImageView);

        bartImageView.animate().translationXBy(-1500);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView bartImageView = findViewById(R.id.bartImageView);

        bartImageView.setX(-1200);
        bartImageView.animate().translationXBy(1200).alpha(1).rotationBy(1800).setDuration(1000);


    }
}
