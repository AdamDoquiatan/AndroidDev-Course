package com.adamdoq.whatstheweather;

import android.content.Context;
import android.hardware.input.InputManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    String result;
    TextView text;

    public void getWeather(View view) {

        DownloadTask task = new DownloadTask();
        TextView textView = findViewById(R.id.cityInput);


        try {
            String city = URLEncoder.encode(textView.getText().toString(), "UTF-8");
            task.execute("https://openweathermap.org/data/2.5/weather?q=" + city + "&appid=b6907d289e10d714a6e88b30761fae22").get();

            InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.hideSoftInputFromWindow(text.getWindowToken(), 0);
        } catch (ExecutionException e) {
            Toast.makeText(MainActivity.this, "Could not find weather :(", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (InterruptedException e) {
            Toast.makeText(MainActivity.this, "Could not find weather :(", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            Toast.makeText(MainActivity.this, "Could not find weather :(", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.weatherResult);
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
                return null;
            } catch(Exception e) {

                Toast.makeText(MainActivity.this, "Could not find weather :(", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
                return null;
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
                    text.setText(result);
                }
            } catch(Exception e) {
                Toast.makeText(MainActivity.this, "Could not find weather :(", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
}
