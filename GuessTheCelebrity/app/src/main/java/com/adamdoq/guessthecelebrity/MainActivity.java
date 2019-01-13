package com.adamdoq.guessthecelebrity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Random rand;

    String html;

    ArrayList<String> celebImageURLs = new ArrayList<>();
    ArrayList<String> celebNames = new ArrayList<>();

    int currentIndex;
    Bitmap currentCelebImage;
    String currentCelebName;

    public void makeSelection(View view) {
        if(view.getTag() != null && view.getTag().toString().equals(String.valueOf(currentIndex))) {
            Toast.makeText(this, "Right!" , Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong! It was " + celebNames.get(currentIndex) , Toast.LENGTH_SHORT).show();
        }
        setupRound();
    }

    public void setupRound() {
        ImageDownloader imageDownloader = new ImageDownloader();
        ImageView imageView = findViewById(R.id.imageView);
        currentIndex = rand.nextInt(celebImageURLs.size());

        try {
            currentCelebImage = imageDownloader.execute(celebImageURLs.get(currentIndex)).get();
            imageView.setImageBitmap(currentCelebImage);

            LinearLayout choices = findViewById(R.id.choices);
            for(int i = 0; i < choices.getChildCount(); i++) {
                Button choice = (Button) choices.getChildAt(i);
                choice.setText(celebNames.get(rand.nextInt(celebNames.size())));
            }

            currentCelebName = celebNames.get(currentIndex);

            Button correctChoice = (Button) choices.getChildAt(rand.nextInt(choices.getChildCount()));
            correctChoice.setText(currentCelebName);
            correctChoice.setTag(currentIndex);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getCelebData() {
        HTMLGetter celebGetter = new HTMLGetter();
        try {
            html = celebGetter.execute("http://www.posh24.se/kandisar").get();

            String[] splitResult = html.split("<div class=\"listedArticles\">");

            Pattern imagePattern = Pattern.compile("<img src=\"(.*?)\"");
            Matcher imageMatcher = imagePattern.matcher(splitResult[0]);

            while(imageMatcher.find()) {
                celebImageURLs.add(imageMatcher.group(1));
            }


            Pattern namePattern = Pattern.compile("alt=\"(.*?)\"");
            Matcher nameMatcher = namePattern.matcher(splitResult[0]);

            while(nameMatcher.find()) {
                celebNames.add(nameMatcher.group(1));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public class HTMLGetter extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection connection = null;

            try {
                url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream in = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while(data != -1) {
                    char current = (char) data;

                    result += current;

                    data = reader.read();
                }
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Failed";
            } catch (IOException e) {
                e.printStackTrace();
                return "Failed";
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rand = new Random();
        getCelebData();
        setupRound();
    }
}
