package com.cs360.campsitelocator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //buttons and recycler view
    RecyclerView recyclerView;
    Button favorite_button, login_button, logout_button, search_button, about_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //buttons location and function assignment
        recyclerView = findViewById(R.id.recyclerView);
        favorite_button = findViewById(R.id.favorite_button);
        login_button = findViewById(R.id.login_button);
        logout_button = findViewById(R.id.logout_button);
        search_button = findViewById(R.id.search_button);
        about_button = findViewById(R.id.about_button);

        //read shared_prefs for login/logout button change
        SharedPreferences clicked = getSharedPreferences("logged_in", MODE_PRIVATE);
        String logged_in = clicked.getString("remember", "");

        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");

        //if logged in, the login button does not show
        if(logged_in.equals("true") || checkbox.equals("true")){
            login_button.setVisibility(View.GONE);
            logout_button.setVisibility(View.VISIBLE);
        }else{
            login_button.setVisibility(View.VISIBLE);
            logout_button.setVisibility(View.GONE);
        }

        //favorite button set
        favorite_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
                startActivity(intent);
            }
        });

        //login button set
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        //logout button set
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });


        //search button set
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        //about button set
        about_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });


    }

    //confirms logout to be completed.  If verified to log out, clears shared preferences and logs the user out, elese goes back to main activity
    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout?");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false");
                editor.apply();

                SharedPreferences clicked = getSharedPreferences("logged_in", MODE_PRIVATE);
                SharedPreferences.Editor check = clicked.edit();
                check.putString("remember", "false");
                check.apply();

                //Refresh Activity
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //goes back to main activity if choose not to log out
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}
