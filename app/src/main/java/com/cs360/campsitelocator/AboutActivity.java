package com.cs360.campsitelocator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//information in the about page
public class AboutActivity extends AppCompatActivity {
    //set variables and text/buttons
    TextView about, email, information;
    String info;
    Button rate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        //locate variables and text boxes/buttons
        about = findViewById(R.id.about);
        email = findViewById(R.id.email);
        information = findViewById(R.id.information);
        rate = findViewById(R.id.rate_us);

        //pulls information from info string instead of cluttering code with a paragraph of text
        info = getString(R.string.info);

        information.setText(info);

        //apply on click of rate button
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://play.google.com/store/apps"));
                startActivity(intent);
            }
        });
    }
}
