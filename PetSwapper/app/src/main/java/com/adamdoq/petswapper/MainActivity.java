package com.adamdoq.petswapper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    String current = "cat";

    public void swapImage(View view) {
        ImageView myImage = (ImageView) findViewById(R.id.image);

        if(current.equals("cat")) {
            myImage.setImageResource(R.drawable.dog1);
            current = "dog";
        } else if (current.equals("dog")) {
            myImage.setImageResource(R.drawable.cartooncatfree);
            current = "cat";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
