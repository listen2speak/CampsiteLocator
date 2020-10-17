package com.cs360.campsitelocator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//add to favorites
public class AddActivity extends AppCompatActivity {
    //text box and buttons
    EditText site_input, location_input, rating_input, info_input, url_input;
    Button add_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //locate buttons on screen
        site_input = findViewById(R.id.site_input);
        location_input = findViewById(R.id.location_input);
        rating_input = findViewById(R.id.rating_input);
        info_input = findViewById(R.id.info_input);
        url_input = findViewById(R.id.url_input);
        add_button = findViewById(R.id.add_button);

        //listen for text change and apply verifyText method
        site_input.addTextChangedListener(verifyText);
        location_input.addTextChangedListener(verifyText);
        rating_input.addTextChangedListener(verifyText);
        info_input.addTextChangedListener(verifyText);
        url_input.addTextChangedListener(verifyText);

        //on add button press, add to favorites database
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);

                    myDB.addBook(site_input.getText().toString().trim(),
                            location_input.getText().toString().trim(),
                            Integer.valueOf(rating_input.getText().toString().trim()),
                            info_input.getText().toString().trim(),
                            url_input.getText().toString().trim());


            }
        });
    }
    //verifies that input to a new database entry is correct by ensuring rating is a number between 1 and 5 and the rest are populated
    private TextWatcher verifyText = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String site = site_input.getText().toString().trim();
            String location = location_input.getText().toString().trim();
            String rating = rating_input.getText().toString().trim();
            String info = info_input.getText().toString().trim();
            String url = url_input.getText().toString().trim();

            add_button.setEnabled(!site.isEmpty() && !location.isEmpty() && rating.matches("1|2|3|4|5") && !info.isEmpty() && !url.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
