package com.cs360.campsitelocator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText site_input, location_input, rating_input, info_input, url_input;
    Button update_button, delete_button, goto_button;

    String id, site, location, rating, info, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        site_input = findViewById(R.id.site_input2);
        location_input = findViewById(R.id.location_input2);
        rating_input = findViewById(R.id.rating_input2);
        info_input = findViewById(R.id.info_input2);
        url_input = findViewById(R.id.url_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        goto_button = findViewById(R.id.goto_button);


        site_input.addTextChangedListener(verifyText);
        location_input.addTextChangedListener(verifyText);
        rating_input.addTextChangedListener(verifyText);

        //first we call this
        getAndSetIntentData();

        //Set action bar title to site name
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(site);
        }

        goto_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(url));
                startActivity(browserIntent);
            }
        });



        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //And only then do we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                site = site_input.getText().toString().trim();
                location = location_input.getText().toString().trim();
                rating = rating_input.getText().toString().trim();
                info = info_input.getText().toString().trim();
                url = url_input.getText().toString().trim();

                myDB.updateData(id, site, location, rating, info, url);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });


    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("site") && getIntent().hasExtra("location") && getIntent().hasExtra("rating") && getIntent().hasExtra("info") && getIntent().hasExtra("url")){
            //getting data from intent
            id = getIntent().getStringExtra("id");
            site = getIntent().getStringExtra("site");
            location = getIntent().getStringExtra("location");
            rating = getIntent().getStringExtra("rating");
            info = getIntent().getStringExtra("info");
            url = getIntent().getStringExtra("url");

            //setting intent data
            site_input.setText(site);
            location_input.setText(location);
            rating_input.setText(rating);
            info_input.setText(info);
            url_input.setText(url);
        }else{
            Toast.makeText(this,"No Data.", Toast.LENGTH_SHORT).show();
        }
    }
    //method to confirm deletion of all items in the favorites database as well as removes check box for favorite item in the campsite page
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + site + "?");
        builder.setMessage("Are you sure you want to delete " + site + "?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences preferences = getSharedPreferences("favorite_box", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember "+site, "false");
                editor.apply();
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
    //method to verify site and location are populated and rating is populated with a numerical value between 1 and 5
    private TextWatcher verifyText = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        //creates strings of each data item to be validate user input
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String site = site_input.getText().toString().trim();
            String location = location_input.getText().toString().trim();
            String rating = rating_input.getText().toString().trim();
            String info = info_input.getText().toString().trim();
            String url = url_input.getText().toString().trim();

            update_button.setEnabled(!site.isEmpty() && !location.isEmpty() && rating.matches("1|2|3|4|5") && !info.isEmpty() && !url.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
