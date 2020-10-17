package com.cs360.campsitelocator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.method.DateTimeKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class WeatherActivity extends AppCompatActivity {

    TextView location, temp, description;
    ImageView icon;
    String iUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // find textview in xml file
        location = findViewById(R.id.loc);
        temp = findViewById(R.id.temp);
        description = findViewById(R.id.description);
        icon = findViewById(R.id.weather_image);

        //Get strings from campsiteActivity
        Intent intent = getIntent();
        String site = intent.getStringExtra("site");

        //Set action bar to site name
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(site);
        }
        findWeather();
    }

    public void findWeather() {
        //get strings for lat and lon from database through campsiteActivity
        Intent intent = getIntent();
        String lat = intent.getStringExtra("lat");
        String lon = intent.getStringExtra("lon");







        String url = "http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=ebbc6c76e1a337f5ff3405ccf12f627f&units=imperial";

        //Get weather info from JSON file through openweathermap API
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);
                    String temp_find = String.valueOf(main_object.getDouble("temp"));
                    String description_find = object.getString("description");
                    String icon_find = object.getString("icon");
                    String city = response.getString("name");

                    //set text for textView items
                    temp.setText("Current Temperature is " + temp_find + (char) 0x00B0 + "F");
                    description.setText(description_find);
                    location.setText(city);

                    iUrl = "http://openweathermap.org/img/wn/"+icon_find + "@2x.png";

                    Picasso.with(WeatherActivity.this).load(iUrl).into(icon);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        }

        );

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);



    }

}
