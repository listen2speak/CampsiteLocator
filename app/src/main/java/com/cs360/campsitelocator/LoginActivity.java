package com.cs360.campsitelocator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {


    //sets variables.  UNFINISHED!
    EditText email, password;
    CheckBox remember;
    Button login;

    private Button loginbutton;


    //set variables and shared preferences for the "remember me" function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        remember = findViewById(R.id.rememberMe);
        login = findViewById(R.id.loginButton);
        loginbutton = (LoginButton) findViewById(R.id.login_button);

        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");

        //if remember me checked, will save login information to checkbox shared preferences
        if(checkbox.equals("true")){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else if(checkbox.equals("false")){
            Toast.makeText(this, "Please Sign In", Toast.LENGTH_SHORT).show();
        }

        //move to main activity when login button selected
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences clicked = getSharedPreferences("logged_in", MODE_PRIVATE);
                SharedPreferences.Editor check = clicked.edit();
                check.putString("remember", "true");
                check.apply();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            }

        });

        //sets shared preferences for remember me button
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "true");
                    editor.apply();
                    Toast.makeText(LoginActivity.this, "Checked", Toast.LENGTH_SHORT).show();
                }else if (!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "false");
                    editor.apply();
                    Toast.makeText(LoginActivity.this, "Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //sets facebook login activity(needs finishing)
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences clicked = getSharedPreferences("logged_in", MODE_PRIVATE);
                SharedPreferences.Editor check = clicked.edit();
                check.putString("remember", "true");
                check.apply();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
