package com.cs360.campsitelocator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.io.InputStream;

public class CampsiteActivity extends AppCompatActivity {

    //Sets variables for buttons and text boxes
    TextView site_name, site_info, site_location;
    Button weather, website, share;
    String id, site, location, info, url, rating, lat, lon;
    ShareDialog shareDialog;
    CallbackManager callbackManager;
    CheckBox favorite;

    // creates instance for screen view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_campsite);

        //assigns location of items on screen
        site_name = findViewById(R.id.campsite_name);
        site_info = findViewById(R.id.campsite_info);
        site_location = findViewById(R.id.campsite_location);

        weather = findViewById(R.id.weather);
        website = findViewById(R.id.website);
        share = findViewById(R.id.facebook);
        favorite = findViewById(R.id.favorite);

        //calls method to load from database all info and assigns to relative location on screen
        getAndSetIntentData();

        //sets shared preferences to remember if favorite button is checked or not.
        SharedPreferences preferences = getSharedPreferences("favorite_box", MODE_PRIVATE);
        String favorite_box = preferences.getString("remember "+site, "");

        //if favorite button has been checked, will ensure that it remains checked in the program
        if (favorite_box.equals("true")){
            favorite.setChecked(true);
        }

        //sets check box to add to favorites
        favorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //sets shared preferences to acknowledge favorites check box is selected in the future
                if (compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("favorite_box", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember "+site, "true");
                    editor.apply();
                    Toast.makeText(CampsiteActivity.this, "Added to Favorites", Toast.LENGTH_SHORT).show();

                    //sends data to favorite activity to be placed in favorites list
                    MyDatabaseHelper myDB = new MyDatabaseHelper(CampsiteActivity.this);
                    myDB.addBook(site.trim(),
                            location.trim(),
                            Integer.valueOf(rating.trim()),
                            info.trim(),
                            url.trim());
                }
                //if unselected, removes check mark from favorites box for the future
                else if (!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("favorite_box", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember "+site, "false");
                    editor.apply();
                    Toast.makeText(CampsiteActivity.this, "Removed from Favorites", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //sets title bar to reflect name of site
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(site);
        }

        //sets listener to redirect to website of camp
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        //sets listener to redirect to weather activity
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CampsiteActivity.this, WeatherActivity.class);
                intent.putExtra("lat", lat);
                intent.putExtra("lon", lon);
                intent.putExtra("site", site);
                startActivity(intent);

            }
        });

        //initialize FB
        callbackManager = CallbackManager.Factory.create();

        shareDialog = new ShareDialog(this);
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });



        //sets listener to post to FB
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareLinkContent linkContent = new ShareLinkContent.Builder().setQuote("Check out this campsite").setContentUrl(Uri.parse(url)).build();


                if(ShareDialog.canShow(ShareLinkContent.class)){
                    shareDialog.show(linkContent);
                }
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

    }


    //sets method to import from database and assign information to the screen
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("site") && getIntent().hasExtra("location") && getIntent().hasExtra("info") &&
                getIntent().hasExtra("url") && getIntent().hasExtra("rating") && getIntent().hasExtra("lat") && getIntent().hasExtra("lon")){
            //getting data from intent
            id = getIntent().getStringExtra("id");
            site = getIntent().getStringExtra("site");
            location = getIntent().getStringExtra("location");
            info = getIntent().getStringExtra("info");
            url = getIntent().getStringExtra("url");
            rating = getIntent().getStringExtra("rating");
            lat = getIntent().getStringExtra("lat");
            lon = getIntent().getStringExtra("lon");

            //setting intent data
            site_name.setText(site);
            site_info.setText(info);
            site_location.setText(location);


        }else{ //catch to have a toast pop up stating no data for this location.
            Toast.makeText(this,"No Data.", Toast.LENGTH_SHORT).show();
        }

    }


}
