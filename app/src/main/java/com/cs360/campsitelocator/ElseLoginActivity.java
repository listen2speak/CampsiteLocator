package com.cs360.campsitelocator;

import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
//sets facebook login NEEDS UPDATE!
public class ElseLoginActivity  extends AppCompatActivity {

    //sets variables
    private LoginButton loginButton;

    private CallbackManager callbackManager;

    //sets oncreate method
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.login_button);

        //create callback manager
        callbackManager = CallbackManager.Factory.create();
        //request users email and profile only.
        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));

        //callback
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    //passes login results to callbackManager
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

    }




}
