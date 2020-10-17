package com.cs360.campsitelocator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LibraryActivity extends AppCompatActivity {

    //set text boxes and buttons
    EditText site_dev, location_dev, state_dev, rating_dev, info_dev, url_dev;
    Button add_dev;

    //assign text and button locations from activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        site_dev = findViewById(R.id.site_dev);
        location_dev = findViewById(R.id.location_dev);
        state_dev = findViewById(R.id.state_dev);
        rating_dev = findViewById(R.id.rating_dev);
        info_dev = findViewById(R.id.info_dev);
        url_dev = findViewById(R.id.url_dev);
        add_dev = findViewById(R.id.add_dev);

        site_dev.addTextChangedListener(verifyText);
        location_dev.addTextChangedListener(verifyText);
        state_dev.addTextChangedListener(verifyText);
        rating_dev.addTextChangedListener(verifyText);

        //dev only add to internal database
        add_dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseHelper devDB = new DatabaseHelper(LibraryActivity.this);
                devDB.setLibrary(site_dev.getText().toString().trim(),
                        location_dev.getText().toString().trim(),
                        state_dev.getText().toString().trim(),
                        info_dev.getText().toString().trim(),
                        url_dev.getText().toString().trim(),
                        Integer.valueOf(rating_dev.getText().toString().trim()));
            }
        });
    }

    private TextWatcher verifyText = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String site = site_dev.getText().toString().trim();
            String location = location_dev.getText().toString().trim();
            String state = state_dev.getText().toString().trim();
            String rating = rating_dev.getText().toString().trim();

            add_dev.setEnabled(!site.isEmpty() && !location.isEmpty() && !state.isEmpty() && rating.matches("1|2|3|4|5"));

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
