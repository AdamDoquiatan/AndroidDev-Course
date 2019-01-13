package com.adamdoq.whatstheweather;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    String result;

    public void getWeather(View view) {

        DownloadTask task = new DownloadTask();
        TextView textView = findViewById(R.id.cityInput);
        String city = textView.getText().toString();

        try {
            task.execute("https://openweathermap.org/data/2.5/weather?q=" + city + "&appid=b6907d289e10d714a6e88b30761fae22").get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        String JSON = "";

        @Override
        protected String doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream in = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while(data != -1) {
                    char current = (char) data;
                    JSON += current;
                    data = reader.read();
                }
                return "It worked!";
            } catch(Exception e) {
                e.printStackTrace();
                return "It didn't work!";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try{
                JSONObject jsonObject = new JSONObject(JSON);

                String weatherInfo = jsonObject.getString("weather");
                JSONArray arr = new JSONArray(weatherInfo);
                for(int i = 0; i < arr.length(); i++) {
                    JSONObject jsonPart = arr.getJSONObject(i);
                    result = jsonPart.getString("main");
                    result +=" : " + jsonPart.getString("description");
                    TextView text = findViewById(R.id.weatherResult);
                    text.setText(result);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
